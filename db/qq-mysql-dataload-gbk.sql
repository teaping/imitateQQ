/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* �ض��� Created on:     2017/6/26 11:29:48                     */
/*==============================================================*/

use qq;

/* �û������� */
INSERT INTO user VALUES('111','123', '�ض���','28');
INSERT INTO user VALUES('222','123', '��1', '30');
INSERT INTO user VALUES('333','123', '��2', '52');
INSERT INTO user VALUES('888','123', '��3', '53');

/* �û����ѱ�Id1��Id2��Ϊ���� */
INSERT INTO friend VALUES('111','222');
INSERT INTO friend VALUES('111','333');
INSERT INTO friend VALUES('888','111');
INSERT INTO friend VALUES('222','333');
