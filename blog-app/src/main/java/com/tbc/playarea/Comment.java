package com.tbc.playarea;

public class Comment<T> {
	private String title;
	private String commentStr;
	private T comment;
	
	public String getTitle() {
		return title;
	}
	public String getCommentStr() {
		return commentStr;
	}
	public T getComment() {
		return comment;
	}
	
	private Comment(CommentBuilder<T> builder) {
		this.title = builder.title;
		this.commentStr = builder.commentStr;
		this.comment = builder.comment;
	}
	
	public static class CommentBuilder<T> {
		private String title;
		private String commentStr;
		private T comment;
		
		public CommentBuilder<T> setCommentStr(String commentStr) {
			this.commentStr = commentStr;
			return this;
		}
		public CommentBuilder<T> setComment(T comment) {
			this.comment = comment;
			return this;
		}
		public CommentBuilder(String title) {
			super();
			this.title = title;
			this.commentStr = null;
			this.comment = null;
		}
		
		public Comment<T> build() {
			return new Comment<>(this);
		}
	}
	
}
