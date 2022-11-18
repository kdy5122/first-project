/*1.회원정보 테이블*/

create table member(
mem_id varchar(20) primary key,/*아이디*/
mem_pwd varchar(64) not null,/*비밀번호*/
mem_name varchar(20) not null,/*이름*/
mem_email varchar(30),/*이메일*/
mem_call char(13),/*전화번호*/
mem_postcode varchar(20),/*우편번호*/
mem_address1 Nvarchar(60),/*주소*/
mem_address2 Nvarchar(60),/*상세 주소*/
mem_address3 Nvarchar(60),/*참고사항*/
mem_grade char(1) default '1',/*등급 0:vip 1:일반회원 1:관리자 */
mem_point int default 1000
);

select * from member;
insert into member values('tss123','1234','관리자','00000','00000','00000','00000','00000','00000','2',default);
insert into member values('11111','11111','일반유저1','11111','11111','11111','11111','11111','11111','1',default);
insert into member values('22222','22222','일반유저1','22222','22222','22222','22222','22222','22222','1',default);
insert into member values('java','java','홍길동','alssddkfjke@hanmail.net','01012345678','41435','대구광역시북구읍내동','22222','22222',default,default);

delete from member where mem_id='tss1234';

update member set mem_grade='2' where mem_id='tss12345';




update member set mem_grade='1' where mem_id='dmsrud2682';
update member set mem_point=1000  where mem_id='dmsrud2682';
select * from member;

update member set mem_pwd ='java';

drop table member;
SELECT * FROM member WHERE mem_id = '11111' AND mem_pwd = '11111' and mem_grade='1';


/*2.상품정보 테이블*/
create table product(
prod_id int auto_increment primary key, 
prod_name varchar(100)not null,
prod_kind varchar(10)not null,
prod_price int(10)not null,
prod_amount int(10)not null, /*재고수량*/
prod_content varchar(1000),
prod_image  Nvarchar(100) not null,
prod_status varchar(1) not null, /*판매유무 판매할수있는상태: 판매불가상태 :n*/
prod_date date
);
select * from product;
insert into product (prod_name,prod_kind,prod_price,prod_amount,prod_content,prod_image,prod_status,prod_date)
values ('라켓','racket',50000,4,'최신고급','racket.jpg','y',now());

insert into product (prod_name,prod_kind,prod_price,prod_amount,prod_content,prod_image,prod_status,prod_date)
values ('라켓','acc',50000,4,'최신고급','racket.jpg','y',now());
select * from product;

select * from Product where (prod_kind='racket' and prod_status='y')  order by prod_id desc limit 0,6;


select * from product order by prod_kind,prod_id desc;



update product set prod_amount=prod_amount-3 where prod_id=4;

drop table product;





-----3.주문정보테이블

create table order_item(
order_id varchar(30) primary key,
mem_id varchar(20) not null ,
o_name varchar(10)not null,/*수령인*/
o_phone char(13) not null,
o_email varchar(30) not null,
o_address1 varchar(30) not null,
o_address2 varchar(30) not null,
o_address3 varchar(30) not null,
o_require varchar(100),
totalPrice int(10) not null, /*총가격*/
usePoint int(10) not null,
o_date timestamp not null,
o_status CHAR(10) NOT NULL default '주문대기'

);

drop table order_item;

update order_item set o_status='처리완료'where mem_id='java';

insert into order_item values('20221101-104131','test22222','김김김','01012345645','dmsrud2682@hanmail.net','대구','북구','읍내동','배송발리',50000,0,'2022-11-01',default);
insert into order_item values('20221102-104131','test22222','김김김','01012345645','dmsrud2682@hanmail.net','대구','북구','읍내동','배송발리',150000,0,'2022-11-02',default);
insert into order_item values('20221103-634131','test33333','김김김','01012345645','dmsrud2682@hanmail.net','대구','북구','읍내동','배송발리',143000,0,'2022-11-03',default);
insert into order_item values('20221104-724131','test44444','김김김','01012345645','dmsrud2682@hanmail.net','대구','북구','읍내동','배송발리',70000,0,'2022-11-04',default);
insert into order_item values('20221107-1544531','test123','김김김','01012345645','dmsrud2682@hanmail.net','대구','북구','읍내동','배송발리',630000,0,'2022-11-07',default);
insert into order_item values('20221110-194131','lee98766','김김김','01012345645','dmsrud2682@hanmail.net','대구','북구','읍내동','배송발리',50400,0,'2022-11-10',default);
insert into order_item values('20221110-104131','loveing30','김김김','01012345645','dmsrud2682@hanmail.net','대구','북구','읍내동','배송발리',112200,0,'2022-11-10',default);
insert into order_item values('20220130-604138','dmsrud2682','김김김','01012345645','dmsrud2682@hanmail.net','대구','북구','읍내동','배송발리',78900,0,'2022-01-30',default);
insert into order_item values('20220110-104171','lee98766','김김김','01012345645','dmsrud2682@hanmail.net','대구','북구','읍내동','배송발리',150200,0,'2022-01-10',default);
insert into order_item values('20220405-104151','test22222','김김김','01012345645','dmsrud2682@hanmail.net','대구','북구','읍내동','배송발리',39000,0,'2022-04-05',default);

select * from order_item;

/*6개월 이내 주문내역 조회*/
select * from order_item where (mem_id='java' and date(o_date)>date(subdate(now(),interval 6 month)))  order by o_date DESC limit 0,5;


/*당일 매출조회*/
/*curdate() 현재 날짜의 년월일*/

select ifnull(sum(totalPrice),0) from order_item 
where (o_date >curdate());


/*일별 매출조회*/
select o_status,substr(order_id,1,8) as daytotal,sum(totalprice) as DtotalPrice
from order_item
group by o_status,substr(order_id,1,8)
having o_status='처리완료' and daytotal like '202211%'
order by daytotal asc;



/*월별 매출조회*/
select o_status,substr(order_id,1,6) as monthtotal,sum(totalprice) as MtotalPrice
from order_item
group by o_status,substr(order_id,1,6)
having o_status='처리완료' and monthtotal like '202211%';



select * from order_item where o_status='처리완료';

--회원별 총 구매금액(처리완료 기준)
select mem_id,o_status,sum(totalPrice)as total,mem_grade
from order_item join  member
using(mem_id)
group by mem_id,o_status;
having o_status='처리완료';--tss124라는 mem_id를 얻음

/*회원별 총구매금액 데이터 page 처리하기위해서 관리자등급은 제외시키고..*/
select count(*)
from member 
where mem_grade != '2'; 



/*회원별 구매총금액 (관리자제외) : 구매이력이 없는 회원들도 0으로 표시하기위해 left조인을 하여 테이블에 나오도록*/
select mem_id,mem_grade,o_status,ifnull(sum(totalPrice),0) as totalPrice
from member left join order_item
using(mem_id)
group by mem_id,mem_grade,o_status
having mem_grade in('0','1')
order by 4 desc;




/*1.주문시 일반등급=>vip회원 등급 update 할 회원 찾기*/
select mem_id,mem_grade,ifnull(sum(totalPrice),0) as totalPrice
from member  join order_item
using(mem_id)
group by mem_id,mem_grade
having totalPrice>500000
and mem_grade='1'
and mem_id='11111';
/*2.해당쿼리에 결과가 나오면 그 멤버의 등급을 vip로 상향*/



/*1.주문취소시 vip => 일반회원등급 update 할 회원 찾기 */
select mem_id,mem_grade,ifnull(sum(totalPrice),0) as totalPrice
from member left join order_item
using(mem_id)
group by mem_id,mem_grade
having totalPrice<=500000
and  mem_grade='0';
and mem_id='dmsrud2682';

select mem_id,mem_grade,o_status,ifnull(sum(totalPrice),0) as totalPrice
from member left join order_item
using(mem_id)
group by mem_id,mem_grade,o_status
having mem_grade ='0'
and totalPrice<=50000;


/*2.해당쿼리에 결과가 나오면 그 멤버의 등급을 하향*/

select * from member left  join order_item using(mem_id);




/*4.주문상세테이블*/

create table order_detail(
order_detailNum int auto_increment primary key,
order_id varchar(30) not null,
prod_id int not null,
prod_name varchar(100)not null,
prod_price int(10)not null,/*추후삭제예정*/
o_amount int(10) not null  /*구매수량*/
);

select * from order_detail;

select prod_name from order_detail join order_item using(order_id)
where mem_id='java';
/*조인을 하게될시 product 상품삭제되면 orderdetail의 상품명을 얻을수가 없어지므로 사용 X
select * from order_detail  full join  product
using(prod_id);
*/
drop table order_detail;

select  order_id,mem_id,o_status,date_format(o_date,'%Y-%m-%d') as o_date,prod_id,prod_name,o_amount,totalPrice 
from order_item join order_detail
using(order_id);

select * from order_item join order_detail using(order_id) order by o_date desc;

delete from order_item where mem_id='tss12345';







/*5.후기게시판*/

select * from review order by board_no desc limit 0,1;
create table review(
board_no int auto_increment primary key,
prod_id int not null,
mem_id varchar(20) not null,
rev_content varchar(10000) not null,
rev_score char(1) not null,
rev_date date, 
rev_fileName varchar(40),
rev_origfileName varchar(40)
);

select * from review;

drop table review;

select * from review  join order_detail
using (mem_id);


insert into review(prod_id,mem_id,rev_content,rev_score,rev_date,rev_fileName,rev_origfileName) 
values('1004','java','만족합니다','5',now(),null,null);

select * from review;
update review set rev_content='제발수정수정수정',rev_score='5',rev_date=now(),rev_fileName='item-4.jpg' where board_no=9;
update review set rev_content='제발수정수정수정',rev_score='5',rev_date=now(),rev_fileName='' where board_no=10;

--글목록 가져오기위한 test 
select board_no,prod_num,rev_content,rev_score,rev_date from review;
select * from review order by board_no desc limit 0,6;
select * from review order by board_no desc limit 5,6;

/*6 포인트율=> 승급기준 금액(추후예정)*/
create table point(
mem_grade char(1) primary key,
point_rate int not null,
upgrade int not null
);

drop table point;

insert into point values('0',2,0);
insert into point values('1',1,1000000);

select * from point;


---7.커뮤니티게시판

CREATE TABLE tboard(
   TBOARD_NUM INT, /*글 번호*/
    MEM_ID VARCHAR(20) NOT NULL, /*회원 아이디*/
   TBOARD_SUBJECT VARCHAR(50) NOT NULL, /*제목*/
   TBOARD_CONTENT VARCHAR(2000) NOT NULL, /*내용*/
   TBOARD_FILE VARCHAR(50) , /*파일이름*/
   TBOARD_IMAGE VARCHAR(50), /*이미지 내용추가 파일이름*/
   TBOARD_RE_REF INT NOT NULL, /*답글의 그룹번호(일반글번호와 동일), 답글을 다는 원글의 그룹번호를 사용 */
   TBOARD_RE_LEV INT NOT NULL, /*답글의 레벨값 (깊이), 원글을 기준으로한다 ex)한번내려가면1 두번내려가면 2 */
   TBOARD_RE_SEQ INT NOT NULL, /*답글의 순서, 원글에 답글이 몇개인지하는 순서대로 카운트.*/ 
   TBOARD_READCOUNT INT DEFAULT 0, /*조회수*/
   TBOARD_DATE DATE, /*작성일*/
   TBOARD_PLACELA VARCHAR(100) , 
   TBOARD_PLACEMA VARCHAR(100) , 
   TBOARD_ADDRESS VARCHAR(100) ,
   PRIMARY KEY(TBOARD_NUM)
);





select * from tboard;

delete from tboard where tboard_num=3;
delete from tboard where tboard_num=5;
drop table tboard;
select count(*) from board; --총글의 개수 구하기
select * from board order by BOARD_RE_REF desc,BOARD_RE_SEQ asc limit 0,10; --리스트 글목록 가져오기 

---8.문의게시판
CREATE TABLE qboard(
   QBOARD_NUM INT, 
    MEM_ID VARCHAR(20) NOT NULL, /*회원 아이디*/
   QBOARD_SUBJECT VARCHAR(50) NOT NULL, /*제목*/
   QBOARD_CONTENT VARCHAR(2000), /*내용*/
   QBOARD_RE_REF INT NOT NULL, /*답글의 그룹번호(일반글번호와 동일), 답글을 다는 원글의 그룹번호를 사용 */
   QBOARD_RE_LEV INT NOT NULL, /*답글의 레벨값 (깊이), 원글을 기준으로한다 ex)한번내려가면1 두번내려가면 2 */
   QBOARD_RE_SEQ INT NOT NULL, /*답글의 순서, 원글에 답글이 몇개인지하는 순서대로 카운트.*/ 
   QBOARD_READCOUNT INT DEFAULT 0, /*조회수*/
   QBOARD_DATE DATE, /*작성일*/
   
   PRIMARY KEY(QBOARD_NUM)
);
--AUTO_INCREMNET 테이블당 한개밖에 안되서 , QAN_NUM과 QNA_RE_LEV를 DAO에서 함께 증가시켜준다.
--따라서 둘다 DAO에서 증가시켜줌
--AUTO_INCREMNET 를 했다면 ALTER TABLE '테이블명' AUTO_INCREMENT=1;
--set SQL_SAFE_UPDATES =0; //SAFE풀고 
--SET @COUNT = 0; 
--UPDATE '테이블명' SET '[AUTO_INCREMNET 열이름]'=@count:=@count+1; //비어있는 숫자를 다시 정렬시켜준다 


select max(qboard_num) from QBOARD;
select * from QBOARD;


drop table qboard;