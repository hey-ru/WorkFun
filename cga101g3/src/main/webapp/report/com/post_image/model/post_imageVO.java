package com.post_image.model;

public class post_imageVO {
	private Integer post_image_id;
	private Integer post_id;
	private byte[] image;
	public Integer getPost_image_id() {
		return post_image_id;
	}
	public void setPost_image_id(Integer post_image_id) {
		this.post_image_id = post_image_id;
	}
	public Integer getPost_id() {
		return post_id;
	}
	public void setPost_id(Integer post_id) {
		this.post_id = post_id;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
}
