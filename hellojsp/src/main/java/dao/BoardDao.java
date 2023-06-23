package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import common.DBConnPool;

import java.util.List;


import dto.Board;
import dto.Criteria;


public class BoardDao {
	
	
	
	public  BoardDao() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 게시글을 등록 합니다.
	 * @param board
	 * @return
	 */
	public int insert(Board board) {
		int res = 0;
		String sql= "insert into board  "
				+ "(num, title, content, id, postdate, visitcount) "
				+ "values (seq_board_num.nextval, "
					+ " ?,?,?, sysdate, 0)";
		// try() 괄호안에 넣어주면 자동으로 close();해준다!!
		try(Connection conn = DBConnPool.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) 
		{		
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getId());
			// insert, update, delete 실행 후 몇건이 처리 되었는지 반환
				res=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	/**
	 * 게시글의 총 갯수를 반환
	 * @return
	 */
	public int getTotalCnt(Criteria criteria) {
		
		int totalCnt=0;
		String sql = "select count(*) "
				+ "from board ";
				if(criteria.getSearchWord() != null &&  !"".equals(criteria.getSearchWord())) {
				sql += "where "+criteria.getSearchField()+" like '%"+criteria.getSearchWord()+"%'";
				}
				sql += "order by num desc";
		
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
	
	/** @param searchField : 검색조건
	 *  @param searchWord  : 검색어
	 * 게시글을 조회합니다.
	 * @return List<Board> : 게시글 목록
	 */
	public List<Board> getList(String searchField, String searchWord) {
		List<Board> boardList = new ArrayList<Board>();
		
		String sql="select * "
				+ "from BOARD ";
				// 검색어가 입력 되었으면 검색조건을 추가합니다.
				if(searchWord != null &&  !"".equals(searchWord)) {
					
				sql += "where "+searchField+" like '%"+searchWord+"%'";
				}
				sql	+= " order by num desc";
				// 검색조건 추가
				try (Connection conn = DBConnPool.getConnection();
						PreparedStatement pstmt = conn.prepareStatement(sql);){
						
						ResultSet rs = pstmt.executeQuery();
					
					while(rs.next()) {
						Board board = new Board();
						board.setNum( rs.getString("num"));
						board.setTitle(rs.getString("title"));
						board.setContent(rs.getString("content"));
						board.setId(rs.getString("id"));
						board.setPostdate(rs.getString("postdate"));
						board.setVisitcount(rs.getString("visitcount"));
						
						boardList.add(board);
						
					}

				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("게시글 조회 중 오류가 발생");
				}
				
				return boardList;
	}
	
	
	
	/** @param searchField : 검색조건
	 *  @param searchWord  : 검색어
	 * 게시글을 조회합니다.
	 * @return List<Board> : 게시글 목록
	 */
	public List<Board> getListPage(Criteria criteria) {
		List<Board> boardList = new ArrayList<Board>();
		
		String sql="select * from (  select rownum rn, t.* from (  select board.* from board ";
				// 검색어가 입력 되었으면 검색조건을 추가합니다.
				if(criteria.getSearchWord() != null &&  !"".equals(criteria.getSearchWord())) {
					
				sql += "where "+criteria.getSearchField()+" like '%"+criteria.getSearchWord()+"%'";
				}
				sql	+= " order by num desc )  t   ) where rn between "+criteria.getStartNo()+" and "+criteria.getEndNo();
				
				
				
				// 검색조건 추가
				try (Connection conn = DBConnPool.getConnection();
						PreparedStatement pstmt = conn.prepareStatement(sql);){
						
						ResultSet rs = pstmt.executeQuery();
					
					while(rs.next()) {
						Board board = new Board();
						board.setNum( rs.getString("num"));
						board.setTitle(rs.getString("title"));
						board.setContent(rs.getString("content"));
						board.setId(rs.getString("id"));
						board.setPostdate(rs.getString("postdate"));
						board.setVisitcount(rs.getString("visitcount"));
						
						boardList.add(board);
						
					}

				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("게시글 조회 중 오류가 발생");
				}
				
				return boardList;
	}
	
	
	/**
	 * 게시글번호를 입력받아 게시글을 조회합니다. 
	 * @param num
	 * @return
	 */
	public Board selectOne(String num) {
		Board board = null;
		String sql = "select * from board where num = ? ";
		
		if(num == null || "".equals(num)) {
			return null;
		}
		
		try (Connection conn = DBConnPool.getConnection();
				PreparedStatement psmt =  conn.prepareStatement(sql);){
			psmt.setString(1, num);
			
			ResultSet rs = psmt.executeQuery();
			
			// 1건의 게시글을 조회 하여 board 객체에 담아줍니다.
			if(rs.next()) {
				board = new Board();
				board.setNum(rs.getString("num"));
				board.setTitle(rs.getString("title"));
				board.setContent(
						// 줄바꿈처리
						rs.getString("content").replace("\r\n"	, "<br/>"));
				board.setId(rs.getString("id"));
				board.setPostdate(rs.getString("postdate"));
				board.setVisitcount(rs.getString("visitcount"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return board;
		
	}
	
	/**
	 * 게시불의 조회수를 1증가 시킵니다. 
	 * @param num
	 * @return
	 */
	public int updateVisitCount(String num) {
		int res = 0;
		String sql="update board "
				+ "set visitcount= visitcount+1 "
				+ "where num=?";
		
		try (	Connection conn = DBConnPool.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			
			pstmt.setString(1, num);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return res;
	}
	
	public int update(Board board) {
		int res = 0;
		String sql = ""
				+ "update board "
				+ "	  set title=?, content=? "
				+ " where num=?";
		
		
		try (Connection conn = DBConnPool.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getNum());
			
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	public int delete(String num) {
		int res = 0;
		String sql = "" +"delete board where num = ?";

		try (Connection conn = DBConnPool.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);){
				
				pstmt.setString(1,num);
				
				
				res = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				return res;
			}
}





















