package com.getmission.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class GetMissionJDBCDAO implements GetMissionDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA104G3";
	String passwd = "BA104G3";
	
	
		private static final String INSERT_STMT = 
			"INSERT INTO mission (mission_no,mission_category,mission_name,mission_des,issuer_mem_no,takecase_mem_no,mission_release_time,mission_due_time,mission_start_time,mission_end_time,mission_state,mission_pattern,mission_pay) VALUES (to_char(sysdate,'yyyymmdd')||'MIS'||LPAD(to_char(MISSION_SEQ.NEXTVAL),9,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT mission_no ,mission_category , mission_name,mission_des,issuer_mem_no,takecase_mem_no,to_char(mission_release_time,'yyyy-mm-dd') mission_release_time,to_char(mission_due_time,'yyyy-mm-dd') mission_due_time,to_char(mission_start_time,'yyyy-mm-dd') mission_start_time,to_char(mission_end_time,'yyyy-mm-dd') mission_end_time,mission_state,mission_pattern,mission_pay FROM mission order by mission_no";
		private static final String GET_ONE_STMT = 
			"SELECT mission_no ,mission_category , mission_name,mission_des,issuer_mem_no,takecase_mem_no,to_char(mission_release_time,'yyyy-mm-dd') mission_release_time,to_char(mission_due_time,'yyyy-mm-dd') mission_due_time,to_char(mission_start_time,'yyyy-mm-dd') mission_start_time,to_char(mission_end_time,'yyyy-mm-dd') mission_end_time,mission_state,mission_pattern,mission_pay FROM mission where mission_no = ?";
		private static final String DELETE = 
			"DELETE FROM mission where mission_no = ?";
		private static final String UPDATE = 
			"UPDATE mission set mission_category=?, mission_name=?, mission_des=?, issuer_mem_no=?, takecase_mem_no=?, mission_release_time=?, mission_due_time=?, mission_start_time=?, mission_end_time=?, mission_state=?, mission_pattern=?, mission_pay=? where mission_no = ?";
		private static final String TAKE_MISSION=
				"UPDATE mission set mission_State=? where mission_no = ?";
			
	@Override
	public void insert(GetMissionVO getMissionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, getMissionVO.getMission_Category());
			pstmt.setString(2, getMissionVO.getMission_Name());
			pstmt.setString(3, getMissionVO.getMission_Des());
			pstmt.setString(4, getMissionVO.getIssuer_Mem_No());
			pstmt.setString(5, getMissionVO.getTakecase_Mem_No());
			pstmt.setDate(6, getMissionVO.getMission_Release_Time());
			pstmt.setDate(7, getMissionVO.getMission_Due_Time());
			pstmt.setDate(8, getMissionVO.getMission_Start_Time());
			pstmt.setDate(9, getMissionVO.getMission_End_Time());
			pstmt.setInt(10, getMissionVO.getMission_State());
			pstmt.setInt(11, getMissionVO.getMission_Pattern());
			pstmt.setDouble(12, getMissionVO.getMission_Pay());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(GetMissionVO getMissionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
	
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, getMissionVO.getMission_Category());
			pstmt.setString(2, getMissionVO.getMission_Name());
			pstmt.setString(3, getMissionVO.getMission_Des());
			pstmt.setString(4, getMissionVO.getIssuer_Mem_No());
			pstmt.setString(5, getMissionVO.getTakecase_Mem_No());
			pstmt.setDate(6, getMissionVO.getMission_Release_Time());
			pstmt.setDate(7, getMissionVO.getMission_Due_Time());
			pstmt.setDate(8, getMissionVO.getMission_Start_Time());
			pstmt.setDate(9, getMissionVO.getMission_End_Time());
			pstmt.setInt(10, getMissionVO.getMission_State());
			pstmt.setInt(11, getMissionVO.getMission_Pattern());
			pstmt.setDouble(12, getMissionVO.getMission_Pay());
			pstmt.setString(13, getMissionVO.getMission_No());

			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch (SQLException se) {

			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}catch (ClassNotFoundException e) {
	
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(String mission_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mission_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public GetMissionVO findByPrimaryKey(String mission_no) {

		GetMissionVO getMissionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mission_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				getMissionVO = new GetMissionVO();
				getMissionVO.setMission_No(rs.getString("mission_no"));
				getMissionVO.setMission_Category(rs.getString("mission_category"));
				getMissionVO.setMission_Name(rs.getString("mission_name"));
				getMissionVO.setMission_Des(rs.getString("mission_des"));
				getMissionVO.setIssuer_Mem_No(rs.getString("issuer_mem_no"));
				getMissionVO.setTakecase_Mem_No(rs.getString("takecase_mem_no"));
				getMissionVO.setMission_Release_Time(rs.getDate("mission_release_time"));
				getMissionVO.setMission_Due_Time(rs.getDate("mission_due_time"));
				getMissionVO.setMission_Start_Time(rs.getDate("mission_start_time"));
				getMissionVO.setMission_End_Time(rs.getDate("mission_end_time"));
				getMissionVO.setMission_State(rs.getInt("mission_state"));
				getMissionVO.setMission_Pattern(rs.getInt("mission_pattern"));
				getMissionVO.setMission_Pay(rs.getDouble("mission_pay"));
				
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return getMissionVO;
	}

	@Override
	public List<GetMissionVO> getAll() {
		List<GetMissionVO> list = new ArrayList<GetMissionVO>();
		GetMissionVO getMissionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				getMissionVO = new GetMissionVO();
				getMissionVO.setMission_No(rs.getString("mission_no"));
				getMissionVO.setMission_Category(rs.getString("mission_category"));
				getMissionVO.setMission_Name(rs.getString("mission_name"));
				getMissionVO.setMission_Des(rs.getString("mission_des"));
				getMissionVO.setIssuer_Mem_No(rs.getString("issuer_mem_no"));
				getMissionVO.setTakecase_Mem_No(rs.getString("takecase_mem_no"));
				getMissionVO.setMission_Release_Time(rs.getDate("mission_release_time"));
				getMissionVO.setMission_Due_Time(rs.getDate("mission_due_time"));
				getMissionVO.setMission_Start_Time(rs.getDate("mission_start_time"));
				getMissionVO.setMission_End_Time(rs.getDate("mission_end_time"));
				getMissionVO.setMission_State(rs.getInt("mission_state"));
				getMissionVO.setMission_Pattern(rs.getInt("mission_pattern"));
				getMissionVO.setMission_Pay(rs.getDouble("mission_pay"));
				list.add(getMissionVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	
	@Override
	public void takeMission(GetMissionVO getMissionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
	
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(TAKE_MISSION);

			
			pstmt.setInt(1, getMissionVO.getMission_State());
		
			pstmt.setString(2, getMissionVO.getMission_No());

			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch (SQLException se) {

			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}catch (ClassNotFoundException e) {
	
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	public static void main(String[] args) {

		GetMissionJDBCDAO dao = new GetMissionJDBCDAO();

		// 新增
		GetMissionVO getMissionVO1 = new GetMissionVO();
		getMissionVO1.setMission_Category("教育");
		getMissionVO1.setMission_Name("救救我Java!!");
		getMissionVO1.setMission_Des("哇!!怎麼辦怎麼辦,我的專題出不來阿 gg思密達!");
		getMissionVO1.setIssuer_Mem_No("M000004");
//		getMissionVO1.setTakecase_Mem_No("M000003");
		getMissionVO1.setMission_Release_Time(java.sql.Date.valueOf("2017-10-27"));
		getMissionVO1.setMission_Due_Time(java.sql.Date.valueOf("2017-11-01"));
//		getMissionVO1.setMission_Start_Time(java.sql.Date.valueOf("2017-10-28"));
//		getMissionVO1.setMission_End_Time(java.sql.Date.valueOf("2017-10-31"));
		getMissionVO1.setMission_State(1);
		getMissionVO1.setMission_Pattern(1);
		getMissionVO1.setMission_Pay(100.00);
		dao.insert(getMissionVO1);
		System.out.println("新增成功...");
		
// 修改
		
//		GetMissionVO getMissionVO2 = new GetMissionVO();
//		getMissionVO2.setMission_No("20171027MIS000000010");
//		getMissionVO2.setMission_Category("教育");
//		getMissionVO2.setMission_Name("救救我Java!!");
//		getMissionVO2.setMission_Des("哇!!怎麼辦怎麼辦,我的專題出不來阿 gg思密達!");
//		getMissionVO2.setIssuer_Mem_No("M000004");
////		getMissionVO2.setTakecase_Mem_No("M000003");
//		getMissionVO2.setMission_Release_Time(java.sql.Date.valueOf("2017-10-27"));
//		getMissionVO2.setMission_Due_Time(java.sql.Date.valueOf("2017-11-01"));
////		getMissionVO2.setMission_Start_Time(java.sql.Date.valueOf("2017-10-28"));
////		getMissionVO2.setMission_End_Time(java.sql.Date.valueOf("2017-10-31"));
//		getMissionVO2.setMission_State(1);
//		getMissionVO2.setMission_Pattern(1);
//		getMissionVO2.setMission_Pay(new Double(100.00));
//		
//		dao.update(getMissionVO2);
//		System.out.println("修改成功...");
////		// 刪除
//		dao.delete("20171027MIS000000009");
		
		

		// 查詢
//		GetMissionVO getMissionVO3 = dao.findByPrimaryKey("20171027MIS000000004");
//		System.out.print(getMissionVO3.getMission_No() + ",");
//		System.out.print(getMissionVO3.getMission_Category() + ",");
//		System.out.print(getMissionVO3.getMission_Name() + ",");
//		System.out.print(getMissionVO3.getMission_Des() + ",");
//		System.out.print(getMissionVO3.getIssuer_Mem_No() + ",");
//		System.out.print(getMissionVO3.getTakecase_Mem_No() + ",");
//		System.out.print(getMissionVO3.getMission_Release_Time() + ",");
//		System.out.print(getMissionVO3.getMission_Due_Time() + ",");
//		System.out.print(getMissionVO3.getMission_Start_Time() + ",");
//		System.out.print(getMissionVO3.getMission_End_Time() + ",");
//		System.out.print(getMissionVO3.getMission_State() + ",");
//		System.out.print(getMissionVO3.getMission_Pattern() + ",");
//		System.out.println(getMissionVO3.getMission_Pay());
//		System.out.println("---------------------");

		// 查詢
		List<GetMissionVO> list = dao.getAll();
		for (GetMissionVO agetMissionVO : list) {
			System.out.print(agetMissionVO.getMission_No() + ",");
			System.out.print(agetMissionVO.getMission_Category() + ",");
			System.out.print(agetMissionVO.getMission_Name() + ",");
			System.out.print(agetMissionVO.getMission_Des() + ",");
			System.out.print(agetMissionVO.getIssuer_Mem_No() + ",");
			System.out.print(agetMissionVO.getTakecase_Mem_No() + ",");
			System.out.print(agetMissionVO.getMission_Release_Time() + ",");
			System.out.print(agetMissionVO.getMission_Due_Time() + ",");
			System.out.print(agetMissionVO.getMission_Start_Time() + ",");
			System.out.print(agetMissionVO.getMission_End_Time() + ",");
			System.out.print(agetMissionVO.getMission_State() + ",");
			System.out.print(agetMissionVO.getMission_Pattern() + ",");
			System.out.println(agetMissionVO.getMission_Pay());
			System.out.println();
		}
	}
}