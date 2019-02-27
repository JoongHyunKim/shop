package com.controller.goods;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.MemberDTO;
import com.service.CartService;
import com.service.GoodsService;

/**
 * Servlet implementation class GoodsListServlet
 */
@WebServlet("/CartListServlet")
public class CartListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 HttpSession session = request.getSession();
	      MemberDTO dto = (MemberDTO)session.getAttribute("logindto");
		 String nextPage = null;
	      if(dto!=null) {
	        String userid =dto.getUserid();
	        CartService service =new CartService();
	        List<CartDTO> list = service.cartList(userid);
	        
	        
	        request.setAttribute("cartList", list);
			nextPage = "cartList.jsp";
	        
	      }else {
	    	  nextPage="cartList.jsp";
			  session.setAttribute("mesg", "비회원 카트리스트입니다.");
			  
		  }
		RequestDispatcher dis = request.getRequestDispatcher(nextPage);
		dis.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}