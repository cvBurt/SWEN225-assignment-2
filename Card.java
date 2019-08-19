import javax.swing.ImageIcon;

public class Card {
	private String type;
	private String id;
	private int startRow;
	private int startCol;
	private ImageIcon img;
	
	public Card(String type, String id) {
		this.type = type;
		this.id = id;
	}
	
	public Card(String type, String id, int startRow, int startCol, ImageIcon img) {
		this.type = type;
		this.id = id;
		this.startRow = startRow;
		this.startCol = startCol;
		this.img = img;
	}

	public String getType() {
		return type;
	}

	public String getId() {
		return id;
	}

	public int getStartRow() {
		return startRow;
	}

	public int getStartCol() {
		return startCol;
	}

	public ImageIcon getImg() {
		return img;
	}
}

