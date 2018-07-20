package duomi.com.httpIvk.param.creditGuard;

import net.sf.json.JSONObject;

public class CreditGuardResult {
	private JSONObject INFOANALYSIS;
	private CreditGuardAntifraud ANTIFRAUD;

	public JSONObject getINFOANALYSIS() {
		return INFOANALYSIS;
	}

	public void setINFOANALYSIS(JSONObject iNFOANALYSIS) {
		INFOANALYSIS = iNFOANALYSIS;
	}

	public CreditGuardAntifraud getANTIFRAUD() {
		return ANTIFRAUD;
	}

	public void setANTIFRAUD(CreditGuardAntifraud aNTIFRAUD) {
		ANTIFRAUD = aNTIFRAUD;
	}
	
}
