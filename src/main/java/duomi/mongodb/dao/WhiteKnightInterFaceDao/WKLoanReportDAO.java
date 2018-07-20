package duomi.mongodb.dao.WhiteKnightInterFaceDao;

import duomi.mongodb.bean.wightknightInterfaecModel.LoanReportModel;

import java.util.List;

/**
 * Created by DEllComputer on 2018/2/28.
 */
public interface WKLoanReportDAO {

    public void add(LoanReportModel loanReportModel);

    public void remove(LoanReportModel loanReportModel);

    public LoanReportModel selectOne(LoanReportModel loanReportModel);

    public List<LoanReportModel> selectList(LoanReportModel loanReportModel);

    public List<LoanReportModel> selectAll();

}
