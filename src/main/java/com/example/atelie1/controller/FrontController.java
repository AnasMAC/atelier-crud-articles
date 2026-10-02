package com.example.atelie1.controller;


import com.example.atelie1.model.Article;
import com.example.atelie1.model.DaoArticle;
import com.sun.net.httpserver.Request;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;


@WebServlet(urlPatterns = {
        "/article",
        "/article/list",
        "/article/new",
        "/article/edit",
        "/article/delete",
        "/article/create",
        "/article/update",

})
public class FrontController  extends HttpServlet {
    private DaoArticle daoArticle;
    public void init(){
        this.daoArticle = DaoArticle.getInstance();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processAction(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processAction(request, response);
    }


    private void processAction(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println("action: "+ action);
        switch (action){
            case "/article":
                listAll(request,response);
                break;
            case "/article/list":
                listAll(request,response);
                break;
            case "/article/new":
                newForme(request,response);
                break;
            case "/article/edit":
                editForm(request,response);
                break;
            case "/article/delete":
                deleteArticle(request,response);
                break;
            case "/article/create":
                createArticle(request,response);
                break;
            case "/article/update":
                updateArticle(request,response);
                break;
            default:
                response.sendError(404,"action not found");
                break;
        }
    }

    private void listAll(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("articles",daoArticle.getAll());
        request.getRequestDispatcher("/WEB-INF/views/listeArticles.jsp").forward(request, response);
    }
    private void newForme(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/Article.jsp").forward(request,response);
    }
    private void editForm(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String code = request.getParameter("code");
        Optional<Article> o = daoArticle.getById(code);
        if(o.isPresent()){
            request.setAttribute("Article",o.get());
        } else {
            request.setAttribute("error","Article NOT FOUND");
        }
        request.getRequestDispatcher("/WEB-INF/views/EditArticle.jsp").forward(request,response);
    }
    private void deleteArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("message");
        request.getSession().removeAttribute("error");
        String code = request.getParameter("code");
        boolean isDeleted = daoArticle.deleteArticle(code);

        // 1. Save messages to the Session, NOT the Request
        if(isDeleted){
            request.getSession().setAttribute("message", "The Article was removed successfully.");
        } else {
            request.getSession().setAttribute("error", "Failed: We weren't able to remove the article. Try again.");
        }

        // 2. Redirect to the Servlet endpoint, NOT the JSP inside WEB-INF
        response.sendRedirect(request.getContextPath() + "/article/list");
    }

    private void createArticle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("message");
        request.getSession().removeAttribute("error");
        String code = request.getParameter("code");
        String destination = request.getParameter("destination");
        Double prix = Double.parseDouble(request.getParameter("prix"));
        Article article = new Article(code, destination, prix);
        boolean isCreated = daoArticle.save(article);

        if(isCreated){
            request.getSession().setAttribute("message", "The Article was saved successfully.");
        } else {
            request.getSession().setAttribute("error", "Failed: We weren't able to save the article. Try again.");
        }

        response.sendRedirect(request.getContextPath() + "/article/list");
    }

    private void updateArticle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("message");
        request.getSession().removeAttribute("error");
        String code = request.getParameter("code");
        String destination = request.getParameter("destination");
        Double prix = Double.parseDouble(request.getParameter("prix"));
        Article article = new Article(code, destination, prix);
        boolean isUpdated = daoArticle.update(article);

        if(isUpdated){
            request.getSession().setAttribute("message", "The Article was updated successfully.");
        } else {
            request.getSession().setAttribute("error", "Failed: We weren't able to update the article. Try again.");
        }

        response.sendRedirect(request.getContextPath() + "/article/list");
    }
}
