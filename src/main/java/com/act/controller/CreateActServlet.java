package com.act.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ac.model.AcServiceImpl;
import com.ac.model.AcVO;
import com.ac_pic.model.AcPicVO;
import com.act.model.ActService;
import com.act.model.ActVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.testany.model.testAc;
import com.util.LocalDateTimeDeserializer;
import com.util.LocalDateTimeSerializer;

import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.Dispatcher;

@WebServlet("/createAct")
public class CreateActServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	    req.setCharacterEncoding("UTF-8");
        res.setContentType("application/json, text/html; charset=UTF-8");
	    res.setCharacterEncoding("UTF-8");
        System.out.println("Fetch Request -> CreateActServelt");
        
		BufferedReader br = req.getReader();
		Gson gson = new Gson();
		GsonBuilder gsonBuilder = new GsonBuilder();  
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());      
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        gson = gsonBuilder.setPrettyPrinting().create();
        
        ActVO actVO = gson.fromJson(br, ActVO.class);
        actVO.setMen_no(3);
        actVO.setAct_status(0);
        actVO.setAct_rate_sum(0);
        actVO.setEval_sum(0);
        actVO.setAct_current_count(1);
        System.out.println("act_title: " + actVO.getAct_title());
        System.out.println("act_content: " + actVO.getAct_content());
        System.out.println("act_Type: " + actVO.getAct_type_no());
        System.out.println("act_loc: " + actVO.getAct_loc());
        System.out.println("act_pl: " + actVO.getAct_pl());
        System.out.println("act_enroll_begin: " + actVO.getAct_enroll_begin());
        System.out.println("act_enroll_end: " + actVO.getAct_enroll_end());
        System.out.println("act_start: " + actVO.getAct_start());
        System.out.println("act_end: " + actVO.getAct_end());
        System.out.println("act_min_count: " + actVO.getAct_min_count());
        System.out.println("act_max_count: " + actVO.getAct_max_count());
        
        ActService actService = new ActService();
        
        actService.createAct(actVO);
        
	    String json = gson.toJson(actVO);
	    PrintWriter pw = res.getWriter();
	    pw.print(json);
  
	}

}
