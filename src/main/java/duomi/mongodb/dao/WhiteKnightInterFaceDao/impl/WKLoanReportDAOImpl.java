package duomi.mongodb.dao.WhiteKnightInterFaceDao.impl;

import duomi.mongodb.bean.wightknightInterfaecModel.LoanReportModel;
import duomi.mongodb.dao.MongodbBaseDao;
import duomi.mongodb.dao.WhiteKnightInterFaceDao.WKLoanReportDAO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by DEllComputer on 2018/2/28.
 */
@Component
public class WKLoanReportDAOImpl extends MongodbBaseDao<LoanReportModel> implements WKLoanReportDAO {
    public void add(LoanReportModel loanReportModel) {
        super.save(loanReportModel);
    }

    public void remove(LoanReportModel loanReportModel) {
        super.deleteByCondition(loanReportModel);
    }

    public LoanReportModel selectOne(LoanReportModel loanReportModel) {
        return super.findOne(loanReportModel);
    }

    public List<LoanReportModel> selectList(LoanReportModel loanReportModel) {
        return super.findByCondition(loanReportModel);
    }

    public List<LoanReportModel> selectAll() {
        return findAll();
    }
}
