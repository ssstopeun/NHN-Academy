CREATE DATABASE IF NOT EXISTS homework;

USE homework;

-- Gender table
CREATE TABLE Gender(
	GenderId INT PRIMARY KEY,
    GenderName	NVARCHAR(3) NOT NULL
);

-- Person table
CREATE TABLE Person(
	PersonId 	INT PRIMARY KEY,
	RRN			VARCHAR(14),
    PersonName 	NVARCHAR(20) NOT NULL,
    GenderId	INT NOT NULL,
    Email		VARCHAR(50),
    PhoneNumber	VARCHAR(15),
    
	UNIQUE KEY unique_Person(RRN),
    CONSTRAINT fk_Person_Gender FOREIGN KEY (GenderId) REFERENCES Gender(GenderId)
);

-- 출생 장소 세부
CREATE TABLE BirthPlaceDetail(
	BirthPlaceId	INT	PRIMARY KEY,
    BirthPlaceName	NVARCHAR(10) NOT NULL
);

-- 출생 신고인 자격
CREATE TABLE BirthReporterQualification(
	ReporterQualificationId	INT	PRIMARY KEY,
    ReporterQualificationName	NVARCHAR(10)
);

-- Birth table
CREATE TABLE Birth (
	PersonId INT PRIMARY KEY,
	BirthPlace NVARCHAR(100) NOT NULL,
	BirthDate DATETIME NOT NULL,
	BirthPlaceId INT NOT NULL,
	ReporterId INT NOT NULL,
	ReporterQualificationId INT NOT NULL,

	CONSTRAINT fk_PersonId FOREIGN KEY (PersonId) REFERENCES Person(PersonId),
    CONSTRAINT fk_BirthReporterId FOREIGN KEY (ReporterId) REFERENCES Person(PersonId),
	CONSTRAINT fk_Birth_BirthPlaceDetail FOREIGN KEY(BirthPlaceId) REFERENCES BirthPlaceDetail(BirthPlaceId),
	CONSTRAINT fk_Birth_BirthReporterQualification FOREIGN KEY(ReporterQualificationId) REFERENCES BirthReporterQualification(ReporterQualificationId)
);

-- 사망신고인자격
CREATE TABLE DeathReporterQualification(
	DeathReporterQualificationId int primary key,
	DeathReporterName nvarchar(20) NOT NULL
);

-- 사망인장소세부
CREATE TABLE DeathPlace(
	DeathPlaceId int primary key,
	DeathPlace nvarchar(20) NOT NULL
);

-- 사망신고서
CREATE TABLE Death(
	DecaintId int primary key,
	DeathPlaceId int NOT NULL,
	DeathAddress nvarchar(100) NOT NULL,
	ReporterId INT NOT NULL,
	ReporterQualification int NOT NULL,
    
	CONSTRAINT fk_DecaintId FOREIGN KEY (DecaintId) REFERENCES Person(PersonId),
	CONSTRAINT fk_DeathPlaceId FOREIGN KEY (DeathPlaceId) REFERENCES DeathPlace(DeathPlaceId),
	CONSTRAINT fk_ReporterQualification FOREIGN KEY (ReporterQualification) REFERENCES DeathReporterQualification(DeathReporterQualificationId),
	CONSTRAINT fk_DeathReporterId FOREIGN KEY (ReporterId) REFERENCES Person(PersonId)
);

-- 증명서발급내역 
CREATE TABLE Certificate (
	CertificateId VARCHAR(17) PRIMARY KEY,
	IssueDate DATE NOT NULL,
	ApplicantId INT NOT NULL,

	CONSTRAINT fk_Certificate_Person FOREIGN KEY(ApplicantId) REFERENCES Person(PersonId)
);

-- 가족관계이름
create table role (
	RoleId int PRIMARY KEY,
    RoleName nvarchar(10)
);

-- 가족관계
create table  RelationShip(
	PersonId int,
    FamilyId int,
    RoleId int,
    
    CONSTRAINT pk_Relationship PRIMARY KEY (PersonId , FamilyId),
	CONSTRAINT fk_Relationship_Me FOREIGN KEY (PersonId) REFERENCES Person(PersonId) on delete cascade on update cascade,
	CONSTRAINT fk_Relationship_Family FOREIGN KEY (FamilyId) REFERENCES Person(PersonId) on delete cascade on update cascade,
	CONSTRAINT fk_Relationship_Role FOREIGN KEY (RoleId) REFERENCES role(RoleId) on delete cascade on update cascade 
);

-- 세대원관계정보
CREATE TABLE MemberRole(
	MemberRoleId 	INT auto_increment PRIMARY KEY,
	MemberRoleName	NVARCHAR(10) NOT NULL,
  
	UNIQUE KEY unique_MemberRole(MemberRoleName)
);

-- 세대구성
CREATE TABLE FamilyComposition(
CompositionId	INT auto_increment PRIMARY KEY,
  HouseholderId	INT NOT NULL,
  
  UNIQUE KEY unique_FamilyComposition(HouseholderId),
  CONSTRAINT fk_FamilyComposition_Person FOREIGN KEY(HouseholderId) REFERENCES Person(PersonId)
);

-- 세대원 정보 
CREATE TABLE Family(
	CompositionId	INT,
	MemberId 		INT,
	ReportedDate	DATE NOT NULL,
	Reason 			NVARCHAR(10) NOT NULL,
	MemberRoleId	INT NOT NULL,
    
	CONSTRAINT pk_Family PRIMARY KEY(CompositionId, MemberId),
	CONSTRAINT fk_Family_FamilyComposition FOREIGN KEY(CompositionId) REFERENCES FamilyComposition(CompositionId),
	CONSTRAINT fk_Family_Person FOREIGN KEY(MemberId) REFERENCES Person(PersonId),
	CONSTRAINT fk_Family_MemberRole FOREIGN KEY(MemberRoleId) REFERENCES MemberRole(MemberRoleId)
);

-- 전입신고
CREATE TABLE MoveInReport (
	PersonId		int not null,
    AddressName 	NVARCHAR(100),
    ReportedDate 	Date not null,
    
    constraint pk_MoveInReport primary key(PersonId, AddressName),
    constraint fk_MoveInReport_Person foreign key(PersonId) references Person(PersonId)
);


-- ============= 데이터 입력 =============
-- Gender
INSERT INTO Gender VALUES(1,"남");
INSERT INTO Gender VALUES(2,"여");

-- 출생신고인자격
INSERT INTO BirthReporterQualification VALUES(1,"부");
INSERT INTO BirthReporterQualification VALUES(2,"모");
INSERT INTO BirthReporterQualification VALUES(3,"호주");
INSERT INTO BirthReporterQualification VALUES(4,"동거친족");
INSERT INTO BirthReporterQualification VALUES(5,"기타");

-- 출생장소세부
INSERT INTO BirthPlaceDetail VALUES(1,"자택");
INSERT INTO BirthPlaceDetail VALUES(2,"병원");
INSERT INTO BirthPlaceDetail VALUES(3,"기타");

-- 사망신고인자격
INSERT INTO DeathReporterQualification VALUES(1,"동거친족");
INSERT INTO DeathReporterQualification VALUES(2,"비동거친족");
INSERT INTO DeathReporterQualification VALUES(3,"동거자");
INSERT INTO DeathReporterQualification VALUES(4,"기타");

-- 사망인장소세부
INSERT INTO DeathPlace VALUES(1,"주택");
INSERT INTO DeathPlace VALUES(2,"의료기관");
INSERT INTO DeathPlace VALUES(3,"사회복지시설(양로원, 고아원 등");
INSERT INTO DeathPlace VALUES(4,"공공시설(학교, 운동장 등)");
INSERT INTO DeathPlace VALUES(5,"산업장");
INSERT INTO DeathPlace VALUES(6,"도로");
INSERT INTO DeathPlace VALUES(7,"상업/서비스시설(상점, 호텔 등)");
INSERT INTO DeathPlace VALUES(8,"농장(논밭, 축사, 양식장 등)");
INSERT INTO DeathPlace VALUES(9,"병원 이송 중 사망");
INSERT INTO DeathPlace VALUES(10,"기타");

-- 가족관계 세부
INSERT INTO role VALUES(1,"본인");
INSERT INTO role VALUES(2,"부");
INSERT INTO role VALUES(3,"모");
INSERT INTO role VALUES(4,"배우자");
INSERT INTO role VALUES(5,"자녀");

-- 세대구성원관계 세부
INSERT INTO MemberRole VALUES(1, '본인');
INSERT INTO MemberRole VALUES(2, '부');
INSERT INTO MemberRole VALUES(3, '모');
INSERT INTO MemberRole VALUES(4, '배우자');
INSERT INTO MemberRole VALUES(5, '자녀');
INSERT INTO MemberRole VALUES(6, '동거인');

-- Person 데이터 입력
INSERT INTO Person (PersonId, RRN, PersonName, GenderId) VALUES(1,'130914-*******', '남길동',1);
INSERT INTO Person (PersonId, RRN, PersonName, GenderId) VALUES(2,'540514-*******', '남석환', 1);
INSERT INTO Person VALUES(3, '790510-*******', '남기준', 1, 'nam@nhnad.co.kr', '010-1234-5678');
INSERT INTO Person (PersonId, RRN, PersonName, GenderId) VALUES(4, '551022-*******', '박한나', 2);
INSERT INTO Person (PersonId, RRN, PersonName, GenderId) VALUES(5, '120315-*******', '남기석', 2);
INSERT INTO Person (PersonId, RRN, PersonName, GenderId) VALUES(6, '820821-*******', '이주은', 2);
INSERT INTO Person (PersonId, RRN, PersonName, GenderId) VALUES(7, '851205-*******', '이선미', 2);
INSERT INTO Person (PersonId, RRN, PersonName, GenderId) VALUES(8, '520630-*******', '이광환', 1);
INSERT INTO Person (PersonId, RRN, PersonName, GenderId) VALUES(9, '281223-*******', '박성철', 1);

-- 출생신고서 데이터 입력
INSERT INTO Birth(PersonId, BirthPlace, BirthDate, BirthPlaceId, ReporterId, ReporterQualificationId) VALUES(2, "경기도 성남시 분당구 대왕판교로645번길", '1954-05-14-18-15', 1, 1,1);
INSERT INTO Birth(PersonId, BirthPlace, BirthDate, BirthPlaceId, ReporterId, ReporterQualificationId) VALUES(3, "경기도 성남시 분당구 대왕판교로645번길", '1979-05-10-03-32', 2, 2,1);
INSERT INTO Birth(PersonId, BirthPlace, BirthDate, BirthPlaceId, ReporterId, ReporterQualificationId) VALUES(5, "경기도 성남시 분당구 대왕판교로645번길", '2012-03-15-14-59', 2, 3, 1);
INSERT INTO Birth(PersonId, BirthPlace, BirthDate, BirthPlaceId, ReporterId, ReporterQualificationId) VALUES(4, "경기도 성남시 분당구 대왕판교로1022번길", '1955-10-22-00-00', 2, 8, 1);
INSERT INTO Birth(PersonId, BirthPlace, BirthDate, BirthPlaceId, ReporterId, ReporterQualificationId) VALUES(6, "경기도 성남시 분당구 대왕판교로0821번길", '1982-08-21-00-00', 2, 9, 1);

-- 사망신고서 데이터 입력
INSERT INTO Death VALUES(1,1,"강원도 고성군 금강산로 290번길",2,2);

-- 증명서 신청 데이터 입력
INSERT INTO Certificate VALUES("12345678-91011121", '2021-10-25',3);
INSERT INTO Certificate VALUES("98765432-10987654", '2021-10-25',3);

-- 남기준 가계도 가족관계 입력
insert into relationship values (1, 1, 1); -- 남길동 본인
insert into relationship values (1, 2, 5); -- 남길동 자녀
insert into relationship values (2, 2, 1); -- 남석환 본인
insert into relationship values (2, 1, 2); -- 남석환 부
insert into relationship values (2, 3, 5); -- 남석환 자녀
insert into relationship values (2, 4, 4); -- 남석환 배우자
insert into relationship values (4, 4, 1); -- 박한나 본인
insert into relationship values (4, 9, 2); -- 박한나 부
insert into relationship values (4, 3, 5); -- 박한나 자녀
insert into relationship values (4, 2, 4); -- 박한나 배우자
insert into relationship values (3, 3, 1); -- 남기준 본인
insert into relationship values (3, 2, 2); -- 남기준 부
insert into relationship values (3, 4, 3); -- 남기준 모
insert into relationship values (3, 5, 5); -- 남기준 자녀
insert into relationship values (3, 6, 4); -- 남기준 배우자
insert into relationship values (6, 6, 1); -- 이주은 본인
insert into relationship values (6, 8, 2); -- 이주은 부
insert into relationship values (6, 5, 5); -- 이주은 자녀
insert into relationship values (6, 3, 4); -- 이주은 배우자
insert into relationship values (5, 5, 1); -- 남기석 본인
insert into relationship values (5, 3, 2); -- 남기석 부
insert into relationship values (5, 6, 3); -- 남기석 모
insert into relationship values (7, 7, 1); -- 이선미 본인
insert into relationship values (7, 8, 2); -- 이선미 부
insert into relationship values (8, 8, 1); -- 이광환 본인
insert into relationship values (8, 7, 5); -- 이광환 자녀
insert into relationship values (8, 6, 5); -- 이광환 자녀
insert into relationship values (9, 9, 1); -- 박성철 본인
insert into relationship values (9, 4, 5); -- 박성철 자녀

-- 세대구성 입력
INSERT INTO FamilyComposition VALUES(1, 3);

-- 세대원 정보 입력
INSERT INTO Family VALUES(1, 6, '2010-02-15', '전입',  4); 
INSERT INTO Family VALUES(1, 5, '2012-03-17', '출생등록', 5);
INSERT INTO Family VALUES(1, 7, '2015-11-29', '전입', 6);
INSERT INTO Family VALUES(1, 3, '2009-10-02', '세대분리', 1);

-- 전입신고 내역 입력
INSERT INTO MoveInReport VALUES(3, "서울시 동작구 상도로 940번길", '2007-10-31');
INSERT INTO MoveInReport VALUES(6, "서울시 동작구 상도로 940번길", '2009-10-02');
INSERT INTO MoveInReport VALUES(3, "경기도 성남시 분당구 불정로 90번길", '2009-10-31');
INSERT INTO MoveInReport VALUES(3, "경기도 성남시 분당구 대왕판교로 645번길", '2013-03-05');
INSERT INTO MoveInReport VALUES(6, "경기도 성남시 분당구 불정로 90번길", '2010-02-15');
INSERT INTO MoveInReport VALUES(5, "경기도 성남시 분당구 불정로 90번길", '2012-03-17');
INSERT INTO MoveInReport VALUES(7, "경기도 성남시 분당구 대왕판교로645번길", '2015-11-29');


-- ============= 조회 =============
-- 가족관계 증명서 상단 조회 영역 ( 발급일/증명확인번호/등록기준지(본적) )
SELECT c.IssueDate AS '발급일', c.CertificateId AS '증명서확인번호', AddressName AS '등록기준지 (본적)'
FROM Person p 
natural join certificate c
natural join MoveInReport m
WHERE RRN = '790510-*******' 
AND 
ReportedDate = (select Max(ReportedDate) from MoveInReport as m where p.PersonId = m.PersonId)
AND
CAST(SUBSTRING(c.CertificateId, 1, 8) AS SIGNED) BETWEEN 0 AND 49999999;

-- 가족구성원 조회영역 (구분/성명/출생연월일/주민등록번호/성별)
SELECT
    RoleName as '구분',
    PersonName as '성명',
    DATE_FORMAT(B.BirthDate, '%Y년 %m월 %d일') as '출생연월일',
    RRN as '주민등록번호',
    GenderName as '성별'
FROM
    (SELECT FamilyId, RoleId FROM Relationship WHERE Relationship.PersonId = 3) as f
JOIN
	Role AS R ON R.RoleId = f.RoleId
JOIN
    Person AS P ON P.PersonId = f.FamilyId
JOIN
    Gender AS G ON G.GenderId = P.GenderId
JOIN
    Birth AS B ON B.PersonId = P.PersonId
ORDER BY R.RoleID;

-- 주민등록등본 상단 조회영역 (발급일/증명확인번호/세대주성명/세대구성사유 및 일자)
SELECT c.IssueDate AS '발급일',
c.CertificateId AS '증명서확인번호',
p.personName AS '세대주성명',
f.Reason AS '세대구성 사유',
f.ReportedDate AS '일자'
From Person p 
INNER JOIN certificate c ON c.applicantId = p.personId
INNER JOIN Family f ON p.personID = f.MemberId
WHERE p.RRN = '790510-*******'
AND
CAST(SUBSTRING(c.CertificateId, 1, 8) AS SIGNED) BETWEEN 50000000 AND 99999999;

-- 전입주소 조회영역 (주소/신고일)
select CASE
        WHEN ROW_NUMBER() OVER () = 1 THEN '현 주소'
        ELSE '전 주소'
		END AS '구분', AddressName AS '주소', ReportedDate AS '신고일'
from Person natural join MoveInReport
where RRN = '790510-*******'
order by ReportedDate desc;

-- 세대구성원 조회영역 (세대주소관계/성명/주민등록번호/신고일/변동사유)
SELECT mr.MemberRoleName AS '세대주 관계', p.PersonName AS '성명', p.RRN AS '주민등록번호', f.ReportedDate AS '신고일', f.Reason AS '변동사유'
FROM Person AS p INNER JOIN Family AS f ON p.PersonID = f.MemberID
				 INNER JOIN FamilyComposition AS fc ON f.CompositionId = fc.CompositionId
                 INNER JOIN memberrole AS mr ON f.MemberroleId = mr.MemberroleId
                 ORDER BY f.MemberroleId;

