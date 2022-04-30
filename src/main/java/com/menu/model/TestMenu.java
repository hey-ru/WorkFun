package com.menu.model;

import java.io.IOException;
import java.util.List;

import org.jvnet.staxex.util.MtomStreamWriter;

import com.mysql.cj.xdevapi.AbstractFilterParams;

public class TestMenu {

	public static void main(String[] args) throws IOException {

		MenuJDBCDAO dao = new MenuJDBCDAO();
		
		MenuVO m3 = new MenuVO();
//		m3.setMenu_id(1004);
//		dao.updatestatus(m3);

		// 新增
//	MenuVO menuVO1 = new MenuVO();
//	menuVO1.setShop_id(102);
//	menuVO1.setItem("牛丼咖哩飯");
//	menuVO1.setPrice(90);
//	dao.insert(menuVO1);

		// 修改
//	MenuVO menuVO2 = new MenuVO();
//	menuVO2.setMenu_id(1010);
//	menuVO2.setShop_id(114);
//	menuVO2.setItem("牛丼+豬排咖哩");
//	menuVO2.setPrice(40);
//	menuVO2.setIs_item(1);
//	dao.update(menuVO2);

//		dao.findByPrimaryKey(1084).toString();

		// 查詢
//		List<MenuVO> list = dao.getAll();
//		for (MenuVO mlist : list) {
//			System.out.println(mlist.toString());
//			System.out.println();
//		}

		// 查詢
//		List<MenuVO> shoplist = dao.getByShopId(101);
//		for (MenuVO slist : shoplist) {
//			System.out.println(slist.toString());
//			System.out.println();
//		}

		// [Service測試]
		MenuService ms = new MenuService();

//		System.out.println(ms.getOneMenuItem(1084));

//		List<MenuVO> mlist = ms.getByShopId(101);
//		for(MenuVO slist : mlist) {
//			System.out.println(slist.toString());
//		}

//		List<MenuVO> mlist1 = ms.getAll();
//		for(MenuVO slist : mlist1) {
//			System.out.println(slist.toString());
//		}

//		System.out.println(ms.addMenuItem(114, "小菜", 30));
//		System.out.println(ms.updateMenuItem(1111, 114, "小菜", 40,1));
//		System.out.println(ms.updateStatus(1051));

	}
}
