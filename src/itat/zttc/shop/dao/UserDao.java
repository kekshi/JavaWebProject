package itat.zttc.shop.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import itat.zttc.shop.model.ShopException;
import itat.zttc.shop.model.User;
import itat.zttc.shop.util.DBUtil;

public class UserDao implements IUserDao {

	@Override
	public void add(User user) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select count(*) from t_user where username=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getInt(1) > 0)
					throw new ShopException("添加的用户已经存在，不能继续添加");
			}
			sql = "insert into t_user values (null,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getNickName());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con);
			DBUtil.close(ps);
			DBUtil.close(rs);
		}
	}

	@Override
	public void delete(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
			String sql = "delete from t_user where id=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con);
			DBUtil.close(ps);
		}
	}

	@Override
	public void update(User user) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = DBUtil.getConnection();
			String sql = "update t_user set password=?,nickname=? where id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con);
			DBUtil.close(ps);
		}
	}

	@Override
	public User load(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select * from t_user where id=?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, user.getId());
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setNickName(rs.getString("nickname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con);
			DBUtil.close(ps);
			DBUtil.close(rs);
		}
		return user;
	}

	@Override
	public List<User> list() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();
		User user = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select * from t_user";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setNickName(rs.getString("nickname"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con);
			DBUtil.close(ps);
			DBUtil.close(rs);
		}
		return users;
	}

	@Override
	public User login(String username, String password) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select * from t_user where username=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setNickName(rs.getString("nickname"));
			}
			if (user == null)
				throw new ShopException("用户名不存在！");
			if (!user.getPassword().equals(password))
				throw new ShopException("用户密码不正确");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con);
			DBUtil.close(ps);
			DBUtil.close(rs);
		}
		return user;
	}

}
