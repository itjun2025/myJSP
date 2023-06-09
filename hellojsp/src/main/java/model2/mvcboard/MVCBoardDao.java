package model2.mvcboard;import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DBConnPool;
import dto.Criteria;



public class MVCBoardDao {
	
	public MVCBoardDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	// 1. 리스트 조회 메서드
	public List<MVCBoardDto> getList1(Criteria cri){
		List<MVCBoardDto> list = new ArrayList<MVCBoardDto>();
		String sql = "SELECT * FROM mvcboard ORDER BY idx DESC";
		
		try (Connection conn = DBConnPool.getConnection();
				PreparedStatement psmt = conn.prepareStatement(sql);){
			
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				MVCBoardDto dto = new MVCBoardDto(
											rs.getString("idx")
											, rs.getString("name")
											, rs.getString("title")
											, rs.getString("content")
											, rs.getString("postdate")
											, rs.getString("ofile")
											, rs.getString("sfile")
											, rs.getInt("downcount")
											, rs.getString("pass")
											, rs.getInt("visitcount")
									);
				
				list.add(dto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	// 리스트 목록 조회 + 검색기능 추가 메서드
	public List<MVCBoardDto> getList(Criteria cri){
		List<MVCBoardDto> list = new ArrayList<MVCBoardDto>();
		String sql = "SELECT * FROM mvcboard ";
		
		// 검색어가 null이 아니라면, 검색 기능을 추가
		if(cri.getSearchWord() != null 
				&& !cri.getSearchWord().equals("")) {
			
			sql += "where " + cri.getSearchField() 
					+" Like '%" + cri.getSearchWord() + "%'";
		}
		
			sql += " ORDER BY idx DESC";
			// 확인해보기 !!
			// System.out.println("검색 sql : " + sql);
		try (Connection conn = DBConnPool.getConnection();
				PreparedStatement psmt = conn.prepareStatement(sql);){
			
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				MVCBoardDto dto = new MVCBoardDto(
											rs.getString("idx")
											, rs.getString("name")
											, rs.getString("title")
											, rs.getString("content")
											, rs.getString("postdate")
											, rs.getString("ofile")
											, rs.getString("sfile")
											, rs.getInt("downcount")
											, rs.getString("pass")
											, rs.getInt("visitcount")
									);
				
				list.add(dto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	// 목록조회 + 페이징메서드
	public List<MVCBoardDto> getListPage(Criteria cri){
		List<MVCBoardDto> list = new ArrayList<MVCBoardDto>();
		String sql = "select * from ("
				+ "    (select tb.* , rownum rn from ( "
				+ 			"SELECT * FROM mvcboard ";
		
		// 검색어가 null이 아니라면, 검색 기능을 추가
		if(cri.getSearchWord() != null 
				&& !cri.getSearchWord().equals("")) {
			
			sql += "where " + cri.getSearchField() 
					+" Like '%" + cri.getSearchWord() + "%'";
		}
		
			sql += " ORDER BY idx DESC"
			
				+ " )  tb)"
				+ ")"
				+ "    where rn between ? and ? ";
			// 확인해보기 !!
			// System.out.println("검색 sql : " + sql);
		try (Connection conn = DBConnPool.getConnection();
				PreparedStatement psmt = conn.prepareStatement(sql);){
			
			// 페이징 처리 - 시작번호와 끝번호을 입력
			psmt.setInt(1, cri.getStartNo());
			psmt.setInt(2, cri.getEndNo());
			
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				MVCBoardDto dto = new MVCBoardDto(
											rs.getString("idx")
											, rs.getString("name")
											, rs.getString("title")
											, rs.getString("content")
											, rs.getString("postdate")
											, rs.getString("ofile")
											, rs.getString("sfile")
											, rs.getInt("downcount")
											, rs.getString("pass")
											, rs.getInt("visitcount")
									);
				
				list.add(dto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	

public int getTotalCnt(Criteria criteria) {
		
		int totalCnt=0;
		String sql = "select count(*) "
				+ "from mvcboard ";
				if(criteria.getSearchWord() != null &&  !"".equals(criteria.getSearchWord())) {
				sql += "where "+criteria.getSearchField()+" like '%"+criteria.getSearchWord()+"%'";
				}
				sql += "order by idx desc";
				System.out.println("sql :" + sql);
		
		try (
				Connection conn = DBConnPool.getConnection();
				PreparedStatement pstmt= conn.prepareStatement(sql);){
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			totalCnt=rs.getInt(1); // 첫번째 컬럼의 값을 반환
			
			rs.close();
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("총게시물의 수를 조회하던중 예외가 발생하였습니다.");
			
		}
		
		
		return totalCnt;
	}


public MVCBoardDto selectOne(String idx) {
	MVCBoardDto dto = new MVCBoardDto();
	String sql = "select * from mvcboard where idx = ?";
	
	try (Connection conn = DBConnPool.getConnection();
		 PreparedStatement pstmt = conn.prepareStatement(sql);){
		
		pstmt.setString(1, idx);
		
		ResultSet rs =  pstmt.executeQuery();
		if(rs.next()) {
			dto.setIdx(rs.getString(1));
			dto.setName(rs.getString(2));
			dto.setTitle(rs.getString(3));
			dto.setContent(rs.getString(4));
			dto.setPostdate(rs.getString(5));
			dto.setOfile(rs.getString(6));
			dto.setSfile(rs.getString(7));
			dto.setDowncount(rs.getInt(8));
			dto.setPass(rs.getString(9));
			dto.setVisitcount(rs.getInt(10));
			
		}
	} catch (SQLException e) {
		System.out.println("게시물 상세보기 조회중 오류가 발생 하였습니다");
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return dto;
}


public boolean comfirmPassword(String pass, String idx) {
	
	boolean res = false;
	String sql="select * from mvcboard where idx = ? and pass= ?";
	
	try (Connection conn = DBConnPool.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);){
		
		pstmt.setString(1, idx);
		pstmt.setString(2, pass);
		
		ResultSet rs =  pstmt.executeQuery();
		
		res = rs.next();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return res;
}


public int delete(String idx) {
	int res = 0;
	String sql ="delete from mvcboard where idx = ? ";
	
	try (Connection conn = DBConnPool.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);){
		
		 pstmt.setString(1, idx);
		 
		 res = pstmt.executeUpdate();
		
		 System.out.println("sql : " + sql);
		 System.out.println("idx : " + idx);
		 System.out.println("res : "+ res);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return res;
}
	
	
//	public int insert(MVCBoardDto MVCBoardDto){
//		int res = 0;
//		String sql="insert into mvcboard (idx, name, title, content, pass) values (seq_board_num.nextval, '아아악', '자료실 제목1 입니다.','내용','1234')";
//		
//		try (Connection conn = DBConnPool.getConnection();
//			PreparedStatement pstmt = conn.prepareStatement(sql);){
//			
//			pstmt.setString(1, MVCBoardDto.getIdx());
//			pstmt.setString(2, MVCBoardDto.getName());
//			pstmt.setString(3, MVCBoardDto.getTitle());
//			pstmt.setString(4, MVCBoardDto.getContent());
//			pstmt.setString(5, MVCBoardDto.getPass());
//			
//			res= pstmt.executeUpdate();
//			
//		
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		return res;
//	}
	

}
