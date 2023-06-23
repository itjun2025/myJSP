package model2.mvcboard;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.JSFunction;

/**
 * Servlet implementation class PassController
 */
@WebServlet("/mvcboard/pass.do")
public class PassController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PassController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// mode : 수정(edit) 삭제(delete)
		// request.setAttribute("mode", request.getParameter("mode"));
		// request.setAttribute("idx", request.getParameter("idx"));
		
		request.getRequestDispatcher("../14MVCBoard/Pass.jsp").forward(request, response);
		
		
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MVCBoardDao dao = new MVCBoardDao();
		
		String idx = request.getParameter("idx");
		String mode = request.getParameter("mode");
		String pass = request.getParameter("pass");
		
		// 게시글의 비밀번호가 일치하는지 확인
		boolean confirmed = dao.comfirmPassword(pass,idx);
		
		// 비밀번호 체크 성공
		if(confirmed ) {
			System.out.println("비밀번호 검증 성공 !");
			// 수정
			if(mode.equals("edit")) {
				response.sendRedirect("../mvcboard/edit.do?idx="+idx);
				
			// 삭제	
			} else if(mode.equals("delete")) {
				int res = dao.delete(idx);
				if(res>0) {
				//삭제성공
					JSFunction.alertLocation(response,"../mvcboard/list.do" ,"삭제되었습니다." );
					
				}else {
				// 삭제 실패	
				// 메세지 처리후 이전 화면으로
					JSFunction.alertBack(response, "게시물 삭제 실패");
				}
					
			}
		// 비밀번호 체크 실패
		// 메세지 처리후 이전화면으로
		} else {
			JSFunction.alertBack(response, "비밀번호 검증에 실패 하였습니다." );
			
		}
	}

}














