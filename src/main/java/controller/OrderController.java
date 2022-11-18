package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.order.CartAddAction;
import action.order.CartListAction;
import action.order.CartListQtyDownAction;
import action.order.CartListQtyUpAction;
import action.order.CartOrderAction;
import action.order.CartOrderformAction;
import action.order.CartRemoveAction;
import action.order.CartRemoveAllAction;
import action.order.ManageOrderListAction;
import action.order.OrderDeleteAction;
import action.order.UpdateOrderStatusAction;
import action.order.UserOrderDetailAction;
import action.order.UserOrderMoneyAction;
import action.order.UserOrderViewAction;
import action.order.UserCancleOrderAction;
import action.order.SalesListAction;
import vo.ActionForward;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.od")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String requestURI = request.getRequestURI();

		String contextPath = request.getContextPath();

		String command = request.getRequestURI().substring(contextPath.length());

		Action action = null;
		ActionForward forward = null;

		System.out.println("command:" + command);// 어떤 요청인지 확인하기 위해 출력

		if (command.equals("/cartAdd.od")) {// 장바구니 담기 요청
			action = new CartAddAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/cartList.od")) {// 장바구니 목록 보여주기 요청
			action = new CartListAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/cartOrderform.od")) {// 장바구니목록에서 주문서작성 요청->주문폼 불러오기
			action = new CartOrderformAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/CartOrder.od")) {// 장바구니목록에서 주문서작성후 주문요청
			action = new CartOrderAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/cartRemove.od")) {// 장바구니목록 삭제요청
			action = new CartRemoveAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/cartRemoveAll.od")) {// 장바구니목록 삭제요청
			action = new CartRemoveAllAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/cartListQtyUp.od")) {// 장바구니목록 수량증가
			action = new CartListQtyUpAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/cartListQtyDown.od")) {// 장바구니목록 수량감소
			action = new CartListQtyDownAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/userOrderView.od")) {// 사용자의 나의 주문내역보기 요청
			action = new UserOrderViewAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();

			}
		} else if (command.equals("/userOrderDetail.od")) {// 사용자의 주문상세보기 요청
			action = new UserOrderDetailAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();

			}
		} else if (command.equals("/userCancleOrder.od")) {// 사용자의 주문취소 요청
			action = new UserCancleOrderAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();

			}	
	
			
			
		} else if (command.equals("/manageOrderList.od")) {// 관리자의 주문보기 요청
			action = new ManageOrderListAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();

			}
			
	
		} else if (command.equals("/updateOrderStatus.od")) {// 관리자의 주문상태 변경요청
			action = new UpdateOrderStatusAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();

			}
		
		} else if (command.equals("/salesList.od")) {// 관리자의 일매출 조회
			action = new SalesListAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		
	 else if (command.equals("/userOrderMoney.od")) {// 관리자의 일매출 조회
		action = new UserOrderMoneyAction();

		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();

		}
	 } else if (command.equals("/orderDelete.od")) {// 관리자의 주문취소처리 (order table 데이터삭제)
				action = new OrderDeleteAction();

				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();

				}
			}

		

		if (forward != null) {
			if (forward.isRedirect()) { // true
				response.sendRedirect(forward.getPath()); // 새요청으로 이동한다.
			} else { // false : 디스패치 - 기존요청 그대로 전달
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}

		} // if문 끝

	}
}
