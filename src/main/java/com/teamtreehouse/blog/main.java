package com.teamtreehouse.blog;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.teamtreehouse.blog.dao.BlogDao;
import com.teamtreehouse.blog.dao.SimpleBlogDao;
import com.teamtreehouse.blog.model.BlogEntry;
import com.teamtreehouse.blog.model.Comment;
import org.apache.log4j.BasicConfigurator;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

/**
 * Created by scott on 4/17/2017.
 */
public class main {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        staticFileLocation("/css");

        BlogDao blogDao = new SimpleBlogDao();
        SimpleBlogDao.addMockData((SimpleBlogDao)blogDao);

        before("/edit/:slug",(req,res)->{
            if(req.cookie("password")==null || !req.cookie("password").equals("admin")){
                String slug = req.params(":slug");
                if(req.session().attribute("from")==null|| !req.session().attribute("from").equals("edit/"+slug)){
                    req.session().attribute("from","edit/"+slug);
                }

                res.redirect("/password");
            }
        });

        before("/new",(req,res)->{
            if(req.cookie("password")==null || !req.cookie("password").equals("admin")){
                if(req.session().attribute("from")==null|| !req.session().attribute("from").equals("new")){
                    req.session().attribute("from","new");
                }
                res.redirect("/password");
            }
        });

        get("/password",(req,res)->{
            Map <Object, String> map = new HashMap<>();
            HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
            return engine.render(new ModelAndView(map,"password.hbs"));
        });

        post("/password", (req,res)->{
            res.cookie("password",req.queryParams("pass"));
            res.redirect("/"+req.session().attribute("from"));
            return null;
        });


        get("/edit/:slug", (req,res)->{
                Map<String, Object> model = new HashMap<>();
                model.put("entry",blogDao.findEntryBySlug(req.params(":slug")));
                return new ModelAndView(model,"edit.hbs");
        },new HandlebarsTemplateEngine());

        post("/edit/:slug",(req, res)->{
            BlogEntry entry = blogDao.findEntryBySlug(req.params(":slug"));
            entry.setContent(req.queryParams("entry"));
            entry.setTitle(req.queryParams("title"));
            res.redirect("/");
            return null;
        });

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("entries",blogDao.findAllEntries());
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/entry/:slug", (req,res)->{
           Map<String, Object> model = new HashMap<>();
            model.put("entry",blogDao.findEntryBySlug(req.params(":slug")));
            return new ModelAndView(model,"details.hbs");
        },new HandlebarsTemplateEngine());

        post("/entry/:slug/comment",(req, res)->{
            BlogEntry entry = blogDao.findEntryBySlug(req.params(":slug"));
            Comment comment = new Comment(req.queryParams("name"),req.queryParams("comment"));
            entry.addComment(comment);
            String param = req.params(":slug");
            res.redirect("/entry/"+param);
            return null;
        });

        get("/new", (req,res)->{
            Map <Object, String> map = new HashMap<>();
            HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
            return engine.render(new ModelAndView(map,"new.hbs"));
        });

        post("/new", (req,res)->{
            BlogEntry entry = new BlogEntry(req.queryParams("title"),req.queryParams("entry"),req.queryParams("slug"));
            blogDao.addEntry(entry);
            res.redirect("/");
            return null;
        });

    }

}
