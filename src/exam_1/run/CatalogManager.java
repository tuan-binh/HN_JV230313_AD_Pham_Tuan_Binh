package exam_1.run;

import exam_1.config.Inputmethods;
import exam_1.controller.CatalogController;
import exam_1.model.Catalog;

public class CatalogManager {
	private CatalogController catalogController;
	
	public CatalogManager(CatalogController catalogController) {
		this.catalogController = catalogController;
		while (true) {
			System.out.println("********************CATALOG-MANAGEMENT********************");
			System.out.println("1. Nhập số danh mục thêm mới và nhập thông tin cho từng danh mục\n" +
					  "2. Hiển thị thông tin tất cả các danh mục \n" +
					  "3. Sửa tên danh mục theo mã danh mục \n" +
					  "4. Xóa danh muc theo mã danh mục (lưu ý ko xóa khi có sản phẩm) \n" +
					  "5. Quay lại");
			System.out.print("Mời bạn lựa chọn: ");
			int choose = Inputmethods.getInteger();
			switch (choose) {
				case 1:
					addNewCatalog();
					break;
				case 2:
					showListCatalog();
					break;
				case 3:
					editCatalog();
					break;
				case 4:
					deleteCatalog();
					break;
				case 5:
					return;
				default:
					System.err.println("Vui lòng nhập lại (1 -> 5)");
					break;
			}
		}
	}
	
	public void addNewCatalog() {
		System.out.print("Bạn muốn nhập vào nhiêu danh mục: ");
		int n = Inputmethods.getInteger();
		for (int i = 0; i < n; i++) {
			System.out.println("Danh mục thứ " + (i + 1));
			Catalog catalog = new Catalog();
			catalog.setCatalogId(catalogController.getNewId());
			catalog.inputData();
			catalogController.save(catalog);
		}
	}
	
	public void showListCatalog() {
		if (catalogController.getAll().size() == 0) {
			System.err.println("Chưa có danh mục nào");
			return;
		}
		for (Catalog c : catalogController.getAll()) {
			System.out.println(c);
		}
	}
	
	public void editCatalog() {
		System.out.print("Bạn muốn sửa danh mục có ID là: ");
		int id = Inputmethods.getInteger();
		Catalog catalog = catalogController.findById(id);
		if (catalog == null) {
			System.err.println("Không tồn tại danh mục này");
			return;
		}
		Catalog newCatalog = new Catalog();
		newCatalog.setCatalogId(catalog.getCatalogId());
		newCatalog.inputData();
		catalogController.save(newCatalog);
	}
	
	public void deleteCatalog() {
		System.out.print("Bạn muốn sửa danh mục có ID là: ");
		int id = Inputmethods.getInteger();
		catalogController.delete(id);
	}
	
}
