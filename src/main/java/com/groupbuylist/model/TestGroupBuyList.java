package com.groupbuylist.model;

import java.util.List;

public class TestGroupBuyList {

	public static void main(String[] args) {

		GroupBuyListJDBCDAO dao = new GroupBuyListJDBCDAO();

		// 新增單筆資料(加入揪團)
//		GroupBuyListVO gblVO1 = new GroupBuyListVO();
//		gblVO1.setGb_id(1002);
//		gblVO1.setBuyer(1013);
//		gblVO1.setBuyer_name("william");
//		gblVO1.setMenu_id(1004);
//		gblVO1.setItem("牛丼咖哩飯");
//		gblVO1.setPrice(90);
//		gblVO1.setQty(3);
//		gblVO1.setRemark("飯多");
//		dao.insertItem(gblVO1);
//		System.out.println("新增一筆參團完成");

		// 某人退出揪團
//		dao.deleteMyGb(1002, 1002);	

		// 更新某人單品項
//		GroupBuyListVO gblVO2 = new GroupBuyListVO();
//		gblVO2.setQty(5);
//		gblVO2.setRemark("醬少");
//		gblVO2.setBuyer(1002);
//		gblVO2.setGbList_id(10008);
////		gblVO2.setBuyer_name("宸宸");
////		gblVO2.setMenu_id(1021);
////		gblVO2.setItem("芥末潤餅");
////		gblVO2.setPrice(45);
////		gblVO2.setTotal(null);
////		gblVO2.setIs_pay(0);
////		gblVO2.setIs_pickup(0);
//		dao.updateItem(gblVO2);

		// 刪除單個訂購項
//		dao.deleteItem(10022);

//搜尋----------------------------------------

		// 1.查詢我的參團 
		List<GroupBuyListVO> mygblist = dao.getMyGB(1002);
		int countmygblist = 1;
		for (GroupBuyListVO mlist : mygblist) {
			System.out.println(mlist);
			System.out.println("---------------------筆數:" + countmygblist++);
		}

		// 查詢 我的單筆明細 OK
//		List<GroupBuyListVO> onelist = dao.getOne(1010, 1002);
//		int countList = 1;
//		for (GroupBuyListVO olist : onelist) {
//			System.out.println(olist);
//			System.out.println("---------------------筆數:" + countList++);
//		}

//		[後台]: 查詢所有參團明細 OK
//		List<GroupBuyListVO> list = dao.getAll();
//		int countList1 = 0;
//		for (GroupBuyListVO allList : list) {
//			System.out.println(allList.toString());
//			System.out.println("---------------------筆數:" + ++countList1);
//		}

		// PK
//		GroupBuyListVO gblVO4 = dao.findByPrimaryKey(10021);
//		System.out.println(gblVO4.toString());

		GroupBuyListService gbListSvc = new GroupBuyListService();
//		gbListSvc.getAll().toString();

	}

}
