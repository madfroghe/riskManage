package duomi.dispatch.response.result;

import java.util.List;

import duomi.dbMap.bean.MobileDetailRecordPo;

public class MobileDetailResult {
	private List<MobileDetailRecordPo> records;

	public List<MobileDetailRecordPo> getRecords() {
		return records;
	}

	public void setRecords(List<MobileDetailRecordPo> records) {
		this.records = records;
	}

}
