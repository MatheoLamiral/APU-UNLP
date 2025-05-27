package ar.edu.unlp.info.oo2.ejercicio02_WallPostApp;

import java.io.IOException;
/**
 * Completar esta clase de acuerdo a lo especificado en el cuadernillo
 *
 */
import java.util.logging.*;
public class WallPostImpl implements WallPost {
	private static final Logger logger = Logger.getLogger(WallPostImpl.class.getName());
	private String text;
	private int likes;
	private boolean isFeatured;

	public WallPostImpl() {
		this.text = "Undefined post";

	}

	public WallPostImpl(String text, int likes, boolean isFeaured) {
		this.text = text;
		this.likes = likes;
		this.isFeatured = isFeaured;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setText(String text) {
		this.text = text;

	}

	@Override
	public int getLikes() {
		return likes;
	}

	@Override
	public void like() {
		this.likes = this.likes + 1;
		if(this.likes == 10) {
			this.exportar("la publicación ha alcanzado los 10 likes","likes10");
		}
	}

	@Override
	public void dislike() {
		if (likes > 0) {
			this.likes = this.likes - 1;
			if(this.likes == 0) {
				this.exportar("La publicación ha alcanzado los 0 likes","likes0");
			}
		}

	}
	
	public void exportar(String mensaje, String nombre) {
		FileHandler fh;
		try {
            fh = new FileHandler("C:\\Users\\ThinkPad\\Desktop\\"+nombre);
            logger.addHandler(fh);

            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            logger.warning(mensaje);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@Override
	public boolean isFeatured() {
		return this.isFeatured;
	}

	@Override
	public void toggleFeatured() {
		this.isFeatured = !this.isFeatured;

	}

	/*
	 * Este mensaje se utiliza para que una instancia de Wallpost se muestre de
	 * forma adecuada
	 */

	@Override
	public String toString() {
		return "WallPost {" + "text: " + getText() + ", likes: '" + getLikes() + "'" + ", featured: '" + isFeatured()
				+ "'" + "}";
	}

}
