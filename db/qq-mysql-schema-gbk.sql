/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* �ض��� Created on:     2017/6/26 11:29:48                     */
/*==============================================================*/

/* �������ݿ� */
CREATE DATABASE  IF NOT EXISTS  qq;

use qq;

/* �û��� */
CREATE TABLE IF NOT EXISTS user (
    user_id varchar(80) not null,   	 /* �û�Id  */
    user_pwd varchar(25)  not null,	 /* �û����� */
    user_name varchar(80) not null,  	 /* �û��� */
    user_icon varchar(100) not null, 	 /* �û�ͷ�� */
    state   int(2) not null,
    PRIMARY KEY (user_id)
);


/* �û����ѱ�Id1��Id2��Ϊ���� */
CREATE TABLE IF NOT EXISTS friend (
    user_id1 varchar(80) not null,   	 /* �û�Id1  */
    user_id2 varchar(80) not null,   	 /* �û�Id2  */
PRIMARY KEY (user_id1, user_id2));
