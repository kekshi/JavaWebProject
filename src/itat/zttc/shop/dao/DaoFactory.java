package itat.zttc.shop.dao;

public class DaoFactory {
	public static IUserDao getUserDao() {
		return new UserDao();
	}
}
