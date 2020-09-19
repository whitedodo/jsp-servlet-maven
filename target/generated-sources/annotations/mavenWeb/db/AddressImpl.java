package mavenWeb.db;

public class AddressImpl implements Address {

	@Override
	public AddressDto getAddress(Integer num) {
		
		AddressDao dao = AddressDao.getInstance();
		return dao.selectAddress(num);
	}

	@Override
	public int updateAddress(AddressDto addressDTO) {
		
		AddressDao dao = AddressDao.getInstance();
		return dao.updateAddress(addressDTO);
	}

	@Override
	public int insertAddress(AddressDto addressDTO) {
		
		AddressDao dao = AddressDao.getInstance();
		return dao.insertAddress(addressDTO);
	}

	@Override
	public int deleteAddress(Integer num) {
		
		AddressDao dao = AddressDao.getInstance();
		return dao.deleteAddress(num);
	}

}
