package duomi.com.httpIvk.param.special;

public class SpecialList {
	private SpecialInfo id;// 通过身份证号查询
	private SpecialInfo cell;// 通过手机号查询
	private SpecialInfo lm_cell;// 通过联系人手机号查询
	private SpecialInfo gid;// 通过身份证号查询

	public SpecialInfo getId() {
		return id;
	}

	public void setId(SpecialInfo id) {
		this.id = id;
	}

	public SpecialInfo getCell() {
		return cell;
	}

	public void setCell(SpecialInfo cell) {
		this.cell = cell;
	}

	public SpecialInfo getLm_cell() {
		return lm_cell;
	}

	public void setLm_cell(SpecialInfo lm_cell) {
		this.lm_cell = lm_cell;
	}

	public SpecialInfo getGid() {
		return gid;
	}

	public void setGid(SpecialInfo gid) {
		this.gid = gid;
	}

}
