package duomi.services;

import duomi.com.httpIvk.param.bankCard.BackCardFourELementResult;
import duomi.com.httpIvk.param.bankCard.BackCardThreeELementResult;
import duomi.com.httpIvk.param.bankCard.BankCardTradeResult;
import duomi.dispatch.request.BackCard3ERequest;
import duomi.dispatch.request.BackCard4ERequest;
import duomi.dispatch.request.BankCardTradeRequest;
import duomi.dispatch.response.ComResponse;

public interface BankCardService {
	// 银行卡三要素验证
	public ComResponse<BackCardThreeELementResult> CheckBankCard3Element(BackCard3ERequest request) throws Exception;

	// 银行卡四要素验证
	public ComResponse<BackCardFourELementResult> CheckBankCard4Element(BackCard4ERequest request) throws Exception;

	// 获取银行卡信息
	public ComResponse<BankCardTradeResult> getBackCardTrade(BankCardTradeRequest request) throws Exception;
}
