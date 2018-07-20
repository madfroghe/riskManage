package duomi.com.httpIvk.house.param;

public class CityInfoInput {
	private String name; // 城市名
	private String firstLetter; // 拼音首字母
	private String spell; // 城市名拼音
	private double lng; // 经度（百度）
	private double lat; // 纬度（百度）

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

}
