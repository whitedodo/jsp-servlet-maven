package mavenWeb.db;

public interface Address {

	public AddressDto getAddress(Integer num);
	public int updateAddress(AddressDto addressDTO);
	public int insertAddress(AddressDto addressDTO); 
	public int deleteAddress(Integer num);
	
}
