select * from chat_message;
select * from chat_room;
select * from user;
select * from join_chat_room;

insert into user values(null, '우동현', 'aaaa', 'aaaa');
insert into user values(null, '김민규', 'bbbb', 'bbbb');
insert into user values(null, '노성진', 'cccc', 'cccc');
insert into user values(null, '진종래', 'dddd', 'dddd');

insert into chat_room values (null, "");
insert into join_chat_room values (null, 1, 1);
insert into join_chat_room values (null, 2, 1);

insert into chat_message values( null, "우동현:gd",1,1,now());
insert into chat_message values( null, "김민규:ㅇㅋ",1,2,now());

-- id에 해당하는 채팅방 조회
select cr.no, 
	   cr.name, 
	   jcr.user_no 
  from chat_room cr, 
	   join_chat_room jcr 
 where cr.no = jcr.chat_room_no
   and jcr.user_no = (select no from user where id = 'bbbb');

-- 채팅방별 메시지 chat_room조회 
select a.no,a.message,a.chat_room_no,a.send_user_no,a.reg_date 
from
	(select no,message,chat_room_no,send_user_no,reg_date 
    from chat_message
    where chat_room_no= 1
    order by no desc 
    limit 0,5) a
    order by no asc
    ;

select a.no,a.message,a.chat_room_no,a.reg_date ,a.name,a.id
from
	(select cm.no,
			cm.message,
            cm.chat_room_no,
            u.id,
            cm.reg_date,
            u.name
	   from chat_message cm, 
		    user u
	  where chat_room_no= 1
        and cm.send_user_no = u.no
        -- and DATE_FORMAT(cm.reg_date, "%Y-%m-%d") = CURDATE()
   order by no desc 
	  limit 0,5) a
order by no asc;
    
    select cm.no,cm.message,cm.chat_room_no,cm.send_user_no,cm.reg_date,u.name
    from chat_message cm, user u
    where chat_room_no= 1
      and cm.send_user_no = u.no
    order by no desc 
    limit 0,5;
    
select no,id,name,password from user;
select no,name,id,password from user
;

insert into chat_room values (null,"");
