package com.lasun.moblile.assistant.push;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySqlite extends SQLiteOpenHelper {
	private static final String DB_NAME = "yuqing.db";
	private static final String WARN = "t_warn";
	private SQLiteDatabase db;
	private static final String TABLE_NAME = "u_message";
	private static final String warnSql = " CREATE TABLE u_message(_ID INTEGER PRIMARY KEY autoincrement,u_title varchar" + "(400),u_details varchar(400),u_detailsText varchar(400),u_picUrl varchar(900),u_pushForce varchar(1),u_detId"
			+ " varchar(40),u_mLoad varchar(40),u_createTime datetime,u_jobTime dateTime,u_searchID varchar(40),u_type varchar(40),u_mWord varcha" + "r(40),u_id varchar(40),u_mType varchar(40),u_receiveTime long,u_newFlag int)";
	public MySqlite(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	public static MySqlite getInstance(Context context) {
		MySqlite mySqlite = new MySqlite(context);
		mySqlite.open();
		return mySqlite;
	}
	public MySqlite(Context context) {
		super(context, DB_NAME, null, 1);
	}
	public void open() {
		db = this.getReadableDatabase();
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(warnSql);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + WARN;
		db.execSQL(sql);
		onCreate(db);
	}
	public void insertMessage(Message message) {
		ContentValues cv = new ContentValues();
		initCV(message, cv);
		db.insert(TABLE_NAME, null, cv);
		closeDB();
	}
	private void closeDB() {
		if (db != null) {
			db.close();
		}
	}
	private void initCV(Message message, ContentValues cv) {
		cv.put("u_title", message.getTitle());
		cv.put("u_details", message.getDetails());
		cv.put("u_detailsText", message.getDetailsText());
		cv.put("u_picUrl", message.getPicUrl());
		cv.put("u_pushForce", message.isPushForce());
		cv.put("u_detId", message.getDetId());
		cv.put("u_mLoad", message.getmLoad());
		cv.put("u_createTime", message.getCreateTime());
		cv.put("u_jobTime", message.getJobTime());
		cv.put("u_searchID", message.getSearchID());
		cv.put("u_type", message.getType());
		cv.put("u_mWord", message.getmWord());
		cv.put("u_id", message.getId());
		cv.put("u_mType", message.getmType());
		cv.put("u_receiveTime", message.getReceiveTime());
		cv.put("u_newFlag", message.getNewFlag());
	}
	public int getRelativeMessageCount(String mWord) {
		int count = 0;
		String selection = "u_mWord like ?";
		Cursor cursor = db.query(TABLE_NAME, new String[] { "u_title", "u_details", "u_detailsText", "u_picUrl", "u_pushForce", "u_detId", "u_mLoad", "u_createTime", "u_jobTime", "u_searchID", "u_type", "u_mWord", "u_id", "u_mType", "u_receiveTime", "u_newFlag" }, selection, new String[] { "%"
				+ mWord + "%" }, null, null, null);
		while (cursor.moveToNext()) {
			count++;
		}
		if (cursor != null) {
			cursor.close();
		}
		closeDB();
		return count;
	}
	public List<Message> getMessageByMTime(long time) {
		String condition = null;
		if (time != 0) {
			condition = String.valueOf(time);
		}
		List<Message> list = new ArrayList<Message>();
		String selection = "u_receiveTime>?";
		String[] selectionArgs = new String[] { condition };
		if (condition == null) {
			selection = null;
			selectionArgs = null;
		}
		Cursor cursor = db.query(TABLE_NAME, new String[] { "u_title", "u_details", "u_detailsText", "u_picUrl", "u_pushForce", "u_detId", "u_mLoad", "u_createTime", "u_jobTime", "u_searchID", "u_type", "u_mWord", "u_id", "u_mType", "u_receiveTime", "u_newFlag" }, selection, selectionArgs, null,
				null, null);
		while (cursor.moveToNext()) {
			Message message = new Message();
			message.setTitle(getTextByCloumnName(cursor, "u_title"));
			message.setDetails(getTextByCloumnName(cursor, "u_details"));
			message.setDetailsText(getTextByCloumnName(cursor, "u_detailsText"));
			message.setPicUrl(getTextByCloumnName(cursor, "u_picUrl"));
			message.setDetId(getTextByCloumnName(cursor, "u_detId"));
			message.setmLoad(getTextByCloumnName(cursor, "u_mLoad"));
			message.setCreateTime(getTextByCloumnName(cursor, "u_createTime"));
			message.setJobTime(getTextByCloumnName(cursor, "u_jobTime"));
			message.setSearchID(getTextByCloumnName(cursor, "u_searchID"));
			message.setType(getTextByCloumnName(cursor, "u_type"));
			message.setmWord(getTextByCloumnName(cursor, "u_mWord"));
			message.setId(getTextByCloumnName(cursor, "u_id"));
			message.setmType(getTextByCloumnName(cursor, "u_mType"));
			message.setPushForce("1".equals(getTextByCloumnName(cursor, "u_pushForce")));
			message.setReceiveTime(cursor.getLong(cursor.getColumnIndex("u_receiveTime")));
			message.setNewFlag(cursor.getInt(cursor.getColumnIndex("u_newFlag")));
			list.add(message);
		}
		if (cursor != null) {
			cursor.close();
		}
		closeDB();
		return list;
	}
	public List<Message> getMessageByMType(String mType) {
		List<Message> list = new ArrayList<Message>();
		String selection = "u_mType=?";
		String[] selectionArgs = new String[] { mType };
		if (mType == null) {
			selection = null;
			selectionArgs = null;
		}
		Cursor cursor = db.query(TABLE_NAME, new String[] { "u_title", "u_details", "u_detailsText", "u_picUrl", "u_pushForce", "u_detId", "u_mLoad", "u_createTime", "u_jobTime", "u_searchID", "u_type", "u_mWord", "u_id", "u_mType", "u_receiveTime", "u_newFlag" }, selection, selectionArgs, null,
				null, null);
		while (cursor.moveToNext()) {
			Message message = new Message();
			message.setTitle(getTextByCloumnName(cursor, "u_title"));
			message.setDetails(getTextByCloumnName(cursor, "u_details"));
			message.setDetailsText(getTextByCloumnName(cursor, "u_detailsText"));
			message.setPicUrl(getTextByCloumnName(cursor, "u_picUrl"));
			message.setDetId(getTextByCloumnName(cursor, "u_detId"));
			message.setmLoad(getTextByCloumnName(cursor, "u_mLoad"));
			message.setCreateTime(getTextByCloumnName(cursor, "u_createTime"));
			message.setJobTime(getTextByCloumnName(cursor, "u_jobTime"));
			message.setSearchID(getTextByCloumnName(cursor, "u_searchID"));
			message.setType(getTextByCloumnName(cursor, "u_type"));
			message.setmWord(getTextByCloumnName(cursor, "u_mWord"));
			message.setId(getTextByCloumnName(cursor, "u_id"));
			message.setmType(getTextByCloumnName(cursor, "u_mType"));
			message.setPushForce("1".equals(getTextByCloumnName(cursor, "u_pushForce")));
			message.setReceiveTime(cursor.getLong(cursor.getColumnIndex("u_receiveTime")));
			message.setNewFlag(cursor.getInt(cursor.getColumnIndex("u_newFlag")));
			list.add(message);
		}
		if (cursor != null) {
			cursor.close();
		}
		closeDB();
		return list;
	}
	public List<Message> getMessageByMword(String mWord) {
		List<Message> list = new ArrayList<Message>();
		String selection = "u_mWord like ?";
		String[] selectionArgs = new String[] { "%" + mWord + "%" };
		if (mWord == null) {
			selection = null;
			selectionArgs = null;
		}
		Cursor cursor = db.query(TABLE_NAME, new String[] { "u_title", "u_details", "u_detailsText", "u_picUrl", "u_pushForce", "u_detId", "u_mLoad", "u_createTime", "u_jobTime", "u_searchID", "u_type", "u_mWord", "u_id", "u_mType", "u_receiveTime", "u_newFlag" }, selection, selectionArgs, null,
				null, null);
		while (cursor.moveToNext()) {
			Message message = new Message();
			message.setTitle(getTextByCloumnName(cursor, "u_title"));
			message.setDetails(getTextByCloumnName(cursor, "u_details"));
			message.setDetailsText(getTextByCloumnName(cursor, "u_detailsText"));
			message.setPicUrl(getTextByCloumnName(cursor, "u_picUrl"));
			message.setDetId(getTextByCloumnName(cursor, "u_detId"));
			message.setmLoad(getTextByCloumnName(cursor, "u_mLoad"));
			message.setCreateTime(getTextByCloumnName(cursor, "u_createTime"));
			message.setJobTime(getTextByCloumnName(cursor, "u_jobTime"));
			message.setSearchID(getTextByCloumnName(cursor, "u_searchID"));
			message.setType(getTextByCloumnName(cursor, "u_type"));
			message.setmWord(getTextByCloumnName(cursor, "u_mWord"));
			message.setId(getTextByCloumnName(cursor, "u_id"));
			message.setmType(getTextByCloumnName(cursor, "u_mType"));
			message.setPushForce("1".equals(getTextByCloumnName(cursor, "u_pushForce")));
			message.setReceiveTime(cursor.getLong(cursor.getColumnIndex("u_receiveTime")));
			message.setNewFlag(cursor.getInt(cursor.getColumnIndex("u_newFlag")));
			list.add(message);
		}
		if (cursor != null) {
			cursor.close();
		}
		closeDB();
		return list;
	}
	public String[] getFrontTwoMessage(String u_mWord) {
		List<Message> list = new ArrayList<Message>();
		String selection = "u_mWord=?";
		String[] selectionArgs = new String[] { u_mWord };
		if (u_mWord == null) {
			selection = null;
			selectionArgs = null;
		}
		Cursor cursor = db.query(TABLE_NAME, new String[] { "u_details" }, selection, selectionArgs, null, null, null);
		while (cursor.moveToNext()) {
			Message message = new Message();
			message.setTitle(getTextByCloumnName(cursor, "u_title"));
			message.setDetails(getTextByCloumnName(cursor, "u_details"));
			message.setDetailsText(getTextByCloumnName(cursor, "u_detailsText"));
			message.setPicUrl(getTextByCloumnName(cursor, "u_picUrl"));
			message.setDetId(getTextByCloumnName(cursor, "u_detId"));
			message.setmLoad(getTextByCloumnName(cursor, "u_mLoad"));
			message.setCreateTime(getTextByCloumnName(cursor, "u_createTime"));
			message.setJobTime(getTextByCloumnName(cursor, "u_jobTime"));
			message.setSearchID(getTextByCloumnName(cursor, "u_searchID"));
			message.setType(getTextByCloumnName(cursor, "u_type"));
			message.setmWord(getTextByCloumnName(cursor, "u_mWord"));
			message.setId(getTextByCloumnName(cursor, "u_id"));
			message.setmType(getTextByCloumnName(cursor, "u_mType"));
			message.setPushForce("1".equals(getTextByCloumnName(cursor, "u_pushForce")));
			message.setReceiveTime(cursor.getLong(cursor.getColumnIndex("u_receiveTime")));
			message.setNewFlag(cursor.getInt(cursor.getColumnIndex("u_newFlag")));
			list.add(message);
		}
		if (cursor != null) {
			cursor.close();
		}
		closeDB();
		return null;
	}
	public void deleteMessage(String mWord) {
		String selection = "u_mWord>?";
		String[] selectionArgs = new String[] { mWord };
		db.delete(TABLE_NAME, selection, selectionArgs);
		closeDB();
	}
	public void deleteMessageById(String messageid) {
		String where = "u_id=?";
		String[] wherevalue = new String[] { messageid };
		db.delete(TABLE_NAME, where, wherevalue);
		closeDB();
	}
	public String getTextByCloumnName(Cursor cursor, String columnName) {
		String result = null;
		result = cursor.getString(cursor.getColumnIndex(columnName));
		return result;
	}
}
