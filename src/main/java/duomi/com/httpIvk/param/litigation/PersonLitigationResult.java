package duomi.com.httpIvk.param.litigation;

public class PersonLitigationResult {
	private Notice<CourtNotice> fygg; // 法院公告
	private Notice<JudgmentDoc> cpws; // 裁判文书
	private Notice<ExecutiveNotice> zxgg; // 执行公告（zxgg）
	private Notice<DiscreditNotice> sxgg; // 失信公告（sxgg）

	public Notice<CourtNotice> getFygg() {
		return fygg;
	}

	public void setFygg(Notice<CourtNotice> fygg) {
		this.fygg = fygg;
	}

	public Notice<JudgmentDoc> getCpws() {
		return cpws;
	}

	public void setCpws(Notice<JudgmentDoc> cpws) {
		this.cpws = cpws;
	}

	public Notice<ExecutiveNotice> getZxgg() {
		return zxgg;
	}

	public void setZxgg(Notice<ExecutiveNotice> zxgg) {
		this.zxgg = zxgg;
	}

	public Notice<DiscreditNotice> getSxgg() {
		return sxgg;
	}

	public void setSxgg(Notice<DiscreditNotice> sxgg) {
		this.sxgg = sxgg;
	}

}
