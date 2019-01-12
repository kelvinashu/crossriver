package za.co.crossriver.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import za.co.crossriver.model.Notification;

@Repository
public class NotificationDao {

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/crossriver";

	static final String USER = "root";
	static final String PASS = "2018menus";

	Connection con = null;

	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(JDBC_DRIVER);
		con = DriverManager.getConnection(DB_URL, USER, PASS);
		return con;
	}

	public List<Notification> readMessages(Boolean isRead, String sentTo) {
		Statement s = null;
		List<Notification> notificationList = new ArrayList<Notification>();
		try {
			s = getConnection().createStatement();
			String sql = "SELECT * FROM notification WHERE is_read = " + isRead + " AND sent_to = '" + sentTo + "'";
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {

				Notification notification = new Notification();

				notification.setId(rs.getInt("id"));
				notification.setDateSent(rs.getDate("date_sent"));
				notification.setDateRead(rs.getDate("date_read"));
				notification.setSentFrom(rs.getString("sent_from"));
				notification.setSentTo(rs.getString("sent_to"));
				notification.setMessage(rs.getString("message"));
				notification.setIsRead(rs.getBoolean("is_read"));

				notificationList.add(notification);

			}

		} catch (Exception e2) {
			e2.printStackTrace();
		}

		return notificationList;
	}

	@SuppressWarnings("null")
	public Notification findById(Integer id) {

		Statement s = null;
		Notification notification = null;
		try {

			s = getConnection().createStatement();

			String sql;
			sql = "SELECT * FROM notification WHERE id = " + id;

			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				notification.setId(rs.getInt("id"));
				notification.setDateSent(rs.getDate("date_sent"));
				notification.setDateRead(rs.getDate("date_read"));
				notification.setSentFrom(rs.getString("sent_from "));
				notification.setSentTo(rs.getString("sent_to"));
				notification.setMessage(rs.getString("message"));
				notification.setIsRead(rs.getBoolean("is_read"));
			}

			rs.close();
			s.close();

		} catch (SQLException se) {
			se.printStackTrace();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} finally {

			try {
				if (con != null) {
					con.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (s != null) {
					s.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return notification;
	}

	public Boolean createNotification(Notification notification) {
		Statement s = null;
		Boolean inserted = false;
		try {

			String sql = "INSERT INTO notification (sent_from, sent_to, message, date_sent, is_read) VALUES('"
					+ notification.getSentFrom() + "', '" + notification.getSentTo() + "', '"
					+ notification.getMessage() + "', '2019-09-05'," + notification.getIsRead() + ")";
			s = getConnection().createStatement();
			inserted = s.executeUpdate(sql) > 0 ? true : false;
			s.close();

		} catch (SQLException se) {

			se.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (con != null) {
					con.close();
				}

			} catch (SQLException e2) {

				e2.getMessage();

			}
			try {

				if (s != null) {
					s.close();
				}

			} catch (SQLException e3) {
				e3.getMessage();
			}

		}

		return inserted;

	}

	public List<Notification> retreiveUserNotifications(String sentTo) {
		List<Notification> notificationList = new ArrayList<Notification>();

		Statement s = null;

		try {

			s = getConnection().createStatement();

			String sql;
			sql = "SELECT * FROM notification WHERE sent_to = '" + sentTo + "' ";

			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {

				Notification notification = new Notification();
				notification.setId(rs.getInt("id"));
				notification.setDateSent(rs.getDate("date_sent"));
				notification.setDateRead(rs.getDate("date_read"));
				notification.setSentFrom(rs.getString("sent_from"));
				notification.setSentTo(rs.getString("sent_to"));
				notification.setMessage(rs.getString("message"));
				notification.setIsRead(rs.getBoolean("is_read"));
				notificationList.add(notification);
			}

			rs.close();
			s.close();

		} catch (SQLException se) {
			se.printStackTrace();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} finally {

			try {
				if (con != null) {
					con.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (s != null) {
					s.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return notificationList;
	}

	public boolean toggleNotification(Integer id, Boolean isRead) {
		boolean updated = false;
		Statement s = null;

		try {
			s = getConnection().createStatement();

			String sql = "UPDATE notification SET is_read = " + isRead + " WHERE id = " + id;
			int a = s.executeUpdate(sql);
			if (a > 0) {
				updated = true;
			}
			con.close();
			s.close();

		} catch (Exception e2) {
			e2.printStackTrace();
		}

		return updated;
	}

}
