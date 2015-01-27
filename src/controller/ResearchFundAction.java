package controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.FundDAO;
import model.FundPriceHistoryDAO;
import model.Model;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.FundBean;
import formbeans.FundIdForm;

public class ResearchFundAction extends Action {

	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;

	private FormBeanFactory<FundIdForm> formBeanFactory = FormBeanFactory
			.getInstance(FundIdForm.class);

	public ResearchFundAction(Model model) {
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();

	}

	public String getName() {
		return "ResearchFund.do";
	}

	public String perform(HttpServletRequest request) {
		// Set up the errors list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			// Set up fund list for nav bar

			FundBean[] fundList = fundDAO.getFunds();
			FundBean[] recommanList = Arrays.copyOf(fundList, 3);

			request.setAttribute("fundList", fundList);
			request.setAttribute("recommandFundList", recommanList);
			request.setAttribute("fundPriceHistoryList", fundPriceHistoryDAO
					.readByFundID(Integer.parseInt(request
							.getParameter("fund_id"))));
			request.setAttribute("fundPriceHistoryName",
					fundDAO.readById(Integer.parseInt(request
							.getParameter("fund_id"))));
			if (request.getParameter("fund_id") != null) {
				if (fundPriceHistoryDAO.readByFundID(Integer.parseInt(request
						.getParameter("fund_id"))) != null) {
					/**int num = fundPriceHistoryDAO.readByFundID(Integer
							.parseInt(request.getParameter("fund_id"))).length;
					double[] dataList = new double[num];
					for (int i = 0; i < num; i++) {
						dataList[i] = fundPriceHistoryDAO.readByFundID(Integer
								.parseInt(request.getParameter("fund_id")))[i]
								.getPrice() / 100.00;
					}

					request.setAttribute("dataList", dataList);

					Date[] dateList = new Date[num];
					for (int i = 0; i < num; i++) {
						dateList[i] = fundPriceHistoryDAO.readByFundID(Integer
								.parseInt(request.getParameter("fund_id")))[i]
								.getPrice_date();
					}
					request.setAttribute("dateList", dateList);*/
				}

				if (request.getParameter("compare_id") != null) {
					/**int num2 = fundPriceHistoryDAO.readByFundID(Integer
							.parseInt(request.getParameter("compare_id"))).length;
					double[] compareList = new double[num2];
					for (int i = 0; i < num2; i++) {
						compareList[i] = fundPriceHistoryDAO
								.readByFundID(Integer.parseInt(request
										.getParameter("compare_id")))[i]
								.getPrice();
					}
					request.setAttribute("compareList", compareList);
					*/
					request.setAttribute("comparePriceHistoryList", fundPriceHistoryDAO.readByFundID(Integer.parseInt(request.getParameter("compare_id"))));
					request.setAttribute("comparePriceHistoryName", fundDAO.readById(Integer.parseInt(request.getParameter("compare_id"))));

				}
			}
			request.setAttribute("now_id", request.getParameter("fund_id"));

			return "customer/research-fund.jsp";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "customer/error.jsp";
		}
	}
}
