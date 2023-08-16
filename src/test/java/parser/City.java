package parser;

public class City {
@Override
	public String toString() {
		return "City [lattitudeD=" + lattitudeD + ", lattitudeM=" + lattitudeM + ", latitudeS=" + latitudeS
				+ ", northSouth=" + northSouth + ", longitudeD=" + longitudeD + ", longitudeM=" + longitudeM
				+ ", longitudeS=" + longitudeS + ", eastWest=" + eastWest + ", city=" + city + ", state=" + state + "]";
	}
	//	"LatD", "LatM", "LatS", "NS", "LonD", "LonM", "LonS", "EW", "City", "State"
	@FieldName(name ="LatD" )
	private int lattitudeD;
	@FieldName(name ="LatM" )
	private int lattitudeM;
	@FieldName(name ="LatS" )
	private int latitudeS;
	@FieldName(name ="NS" )
	private String northSouth;
	
	@FieldName(name ="LonD" )
	private int longitudeD;
	@FieldName(name ="LonM" )
	private int longitudeM;
	@FieldName(name ="LonS" )
	private int longitudeS;
	
	@FieldName(name ="EW" )
	private String eastWest;
	@FieldName(name ="City" )
	private String city;
	@FieldName(name ="State" )
	private String state;
	public int getLattitudeD() {
		return lattitudeD;
	}
	public void setLattitudeD(int lattitudeD) {
		this.lattitudeD = lattitudeD;
	}
	public int getLattitudeM() {
		return lattitudeM;
	}
	public void setLattitudeM(int lattitudeM) {
		this.lattitudeM = lattitudeM;
	}
	public int getLatitudeS() {
		return latitudeS;
	}
	public void setLatitudeS(int latitudeS) {
		this.latitudeS = latitudeS;
	}
	public String getNorthSouth() {
		return northSouth;
	}
	public void setNorthSouth(String northSouth) {
		this.northSouth = northSouth;
	}
	public int getLongitudeD() {
		return longitudeD;
	}
	public void setLongitudeD(int longitudeD) {
		this.longitudeD = longitudeD;
	}
	public int getLongitudeM() {
		return longitudeM;
	}
	public void setLongitudeM(int longitudeM) {
		this.longitudeM = longitudeM;
	}
	public int getLongitudeS() {
		return longitudeS;
	}
	public void setLongitudeS(int longitudeS) {
		this.longitudeS = longitudeS;
	}
	public String getEastWest() {
		return eastWest;
	}
	public void setEastWest(String eastWest) {
		this.eastWest = eastWest;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
