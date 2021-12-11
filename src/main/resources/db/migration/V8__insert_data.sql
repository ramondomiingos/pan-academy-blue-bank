-- BLOCK OF DROPS
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `transactions`;
DROP TABLE IF EXISTS `accounts`;
DROP TABLE IF EXISTS `addresses`;
DROP TABLE IF EXISTS `clients`;

-- BLOCK OF CREATES
CREATE TABLE `clients` (
  `client_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `birth_date` date NOT NULL,
  `mother_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `cellphone` varchar(255) DEFAULT NULL,
  `status` enum('A','B','C') NOT NULL,
  `type` enum('NP','LP') NOT NULL,
  `registration` varchar(255) NOT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE KEY `registration` (`registration`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `addresses` (
  `address_id` bigint NOT NULL AUTO_INCREMENT,
  `street` varchar(255) NOT NULL,
  `address_number` varchar(255) DEFAULT NULL,
  `details` varchar(255) DEFAULT NULL,
  `neighborhood` varchar(255) NOT NULL,
  `zip` varchar(8) NOT NULL,
  `city` varchar(255) NOT NULL,
  `state` char(2) NOT NULL,
  `client_id` bigint DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `client_id` (`client_id`),
  CONSTRAINT `addresses_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `accounts` (
  `account_id` bigint NOT NULL AUTO_INCREMENT,
  `agency_number` bigint NOT NULL,
  `account_number` bigint NOT NULL,
  `account_digit` char(1) NOT NULL,
  `balance` decimal(10,2) NOT NULL DEFAULT '0.00',
  `type` enum('CA','SA') NOT NULL,
  `status` enum('A','B','C') NOT NULL,
  `client_id` bigint NOT NULL,
  PRIMARY KEY (`account_id`),
  KEY `agency_number` (`agency_number`,`account_number`),
  KEY `client_id` (`client_id`),
  CONSTRAINT `accounts_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `transactions` (
  `transaction_id` bigint NOT NULL AUTO_INCREMENT,
  `origin_account` bigint DEFAULT NULL,
  `destination_account` bigint DEFAULT NULL,
  `claim` enum('C','D') NOT NULL,
  `type` enum('DEPOSIT','DOC','PAYMENT','PIX','TED','WITHDRAW') NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `origin_account` (`origin_account`),
  KEY `destination_account` (`destination_account`),
  CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`origin_account`) REFERENCES `accounts` (`account_id`),
  CONSTRAINT `transactions_ibfk_2` FOREIGN KEY (`destination_account`) REFERENCES `accounts` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `users` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `client_id` bigint DEFAULT NULL,
  `role` enum('A','U') NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  KEY `client_id` (`client_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4;

-- BLOCK OF INSERTS
INSERT INTO `clients` VALUES
 (1,'Rodrigo','Levi Rafael Nunes','1985-12-09','Lavínia Mariane','rodrigolevirafaelnunes..rodrigolevirafaelnunes@3dmaker.com.br','3139012825','31988651221','A','NP','87715567531')
,(2,'Ester','Clara Sophie Assis','1992-05-05','Natália Lavínia Lúcia','esterclarasophieassis-75@salvagninigroup.com','5129143274','51994299065','A','NP','55538090701')
,(3,'Gael','Rodrigo Enzo Barbosa','1959-05-19','Gabriela Giovana Alice','gaelrodrigoenzobarbosa_@viavaleseguros.com.br','8428974736','84984500384','A','NP','52327334438')
,(4,'Roberto','Bernardo Castro','1942-12-01','Luna Jaqueline','robertobernardocastro__robertobernardocastro@ozsurfing.com.br','5135713709','51991309557','A','NP','09339714199')
,(5,'Emily','Cecília Vieira','1946-09-01','Esther Bruna Sueli','emilyceciliavieira__emilyceciliavieira@quarttus.com.br','5128628688','51996755051','A','NP','62210627648')
,(6,'Nicole','Patrícia Sandra da Conceição','1952-10-01','Jennifer Tereza','nicolepatriciasandradaconceicao__nicolepatriciasandradaconceicao@hormail.com','853755212','85989665166','A','NP','56500497937')
,(7,'Enzo','Ruan Danilo Duarte','1978-03-15','Clara Maya Aline','enzoruandaniloduarte__enzoruandaniloduarte@sistectecnologia.com.br','9125925249','91987350239','A','NP','99603026875')
,(8,'Caroline','Gabrielly Luna Duarte','1944-08-13','Stefany Hadassa Isabel','carolinegabriellylunaduarte__carolinegabriellylunaduarte@p4ed.com','6927977110','69999669584','A','NP','89893754445')
,(9,'Sophie','Carla da Mata','1996-09-08','Mariane Vitória Elaine','sophiecarladamata__sophiecarladamata@salera.com.br','5438703075','54982353690','A','NP','98175650176')
,(10,'Paulo','Henrique Pietro Campos','1969-06-10','Analu Antonella','paulohenriquepietrocampos-70@facebook.com','6835415130','68987274321','A','NP','08665222472')
,(11,'Isabel','Daniela Flávia Dias','1964-02-15','Betina Lorena','iisabeldanielaflaviadias@msaengenharia.com.br','9438464644','94987013679','A','NP','95651910611')
,(12,'Tiago','Renan Ribeiro','1987-06-20','Elisa Giovanna Isadora','tiagorenanribeiro..tiagorenanribeiro@tribunadeindaia.com.br','9139921844','91988680820','A','NP','97911692688')
,(13,'Pedro','Caleb Aparício','1997-01-21','Elisa Giovanna Isadora','elisagiovanaisadora@tribunadeindaia.com.br','1526460973','15995765908','A','NP','93254796217')
,(14,'Jennifer','Esther Sara Peixoto','1967-08-12','Luiza Betina','jenniferesthersarapeixoto..jenniferesthersarapeixoto@mrv.com.br','8438341639','84987982774','C','NP','85819953908')
,(15,'Bernardo','Juan Almada','1992-03-24','Nair Marli','bernardojuanalmada_@graficajardim.com.br','6439930387','64995226507','A','NP','11225920353')
,(16,'Vinicius','Fernando Rodrigo Alves','1997-02-25','Tereza Beatriz Yasmin','viniciusfernandorodrigoalves..viniciusfernandorodrigoalves@eton.com.br','6936309593','69982962448','B','NP','01553812298')
,(17,'Otávio','Augusto Galvão','1957-03-23','Esther Raquel','otavioaugustogalvao_@juliosimoes.com.br','9235968435','92983670673','A','NP','85171274207')
,(18,'Silvana','Ayla Monteiro','1989-03-25','Malu Jéssica','ssilvanaaylamonteiro@dye.com.br','4338566628','43997829015','A','NP','11758390514')
,(19,'Camila','Lavínia Figueiredo','1999-08-21','Vitória Carolina Stefany','ccamilalaviniafigueiredo@semco.com.br','4135903185','41983724796','A','NP','63728192953')
,(20,'Pietro','Benjamin Bento Alves','1955-01-13','Gabrielly Sarah Tereza','pietrobenjaminbentoalves__pietrobenjaminbentoalves@pronta.com.br','9826343851','98984997135','A','NP','38919791260')
,(21,'Regina Carolina','Malu da Mata','1951-07-03','Eloá Isabel','reginacarolinamaludamata-78@ceuazul.ind.br','9629662075','96993269656','A','NP','67446295309')
,(22,'Elaine','Eliane Ramos','2002-05-11','Josefa Clara','elaineelianeramos..elaineelianeramos@mabeitex.com.br','5129072753','51985235705','A','NP','55709557986')
,(23,'Isaac','Igor Peixoto','1953-12-01','Rita Stella','isaacigorpeixoto_@bucaneiro.com.br','8338538335','83998165674','A','NP','20383470188')
,(24,'Rosângela','Milena Lúcia Cardoso','1996-07-23','Isabella Agatha','rrosangelamilenaluciacardoso@cdcd.com.br','2736762892','27986127808','A','NP','92567247076')
,(25,'Henrique','Anthony Erick Ribeiro','1945-07-12','Luna Sophia Olivia','hhenriqueanthonyerickribeiro@psq.med.br','9139285607','91989508439','A','NP','30830023194')
,(26,'Yuri','Igor Duarte','1952-01-20','Elisa Lara','yyuriigorduarte@kuehne-nagel.com','6937277596','69993591895','A','NP','30980604923')
,(27,'Felipe','Emanuel Gael Brito','1948-10-02','Laís Rosângela Sabrina','felipeemanuelgaelbrito_@moppe.com.br','7928934413','79986154129','A','NP','21236828372')
,(28,'Benício','Ricardo da Rosa','1984-11-17','Sarah Kamilly Luiza','benicioricardodarosa..benicioricardodarosa@focustg.com.br','873894-6433','87996569913','A','NP','89480673541')
,(29,'Henrique','João Matheus Ferreira','1948-06-19','Aurora Andrea Rosângela','henriquejoaomatheusferreira..henriquejoaomatheusferreira@corpus.com.br','2138170711','21998076593','A','NP','39735334828')
,(30,'Sueli','Eloá Catarina Farias','1963-06-18','Ana Julia Giovana','ssuelieloacatarinafarias@cafefrossard.com','8225101793','82997353232','A','NP','84884856899')
,(31,'Rosângela','Emily Mirella Ramos','1970-05-02','Nair Analu Luiza','rosangelaemilymirellaramos..rosangelaemilymirellaramos@terapeutaholistica.com.br','1636084875','16986221138','A','NP','61892129213')
,(32,'Cristiane','Eduarda Mendes','1972-12-07','Sophie Rayssa','cristianeeduardamendes-84@transmazza.com.br','27 3756-2176','27981140748','A','NP','81490924124');

INSERT INTO `addresses` VALUES
 (1,'Rua L','638','','Pompeu','34518020','Sabará','MG',1)
,(2,'Avenida Zero Hora','645','casa','Jardim Algarve','94858000','Alvorada','RS',2)
,(3,'Rua Cícero Gadê','379','ap 3','Nova Betânia','59611370','Mossoró','RN',3)
,(4,'Beco A','213','','Cascata','91712084','Porto Alegre','RS',4)
,(5,'Rua Adolfo Jaeger','729','casa','Ouro Branco','93415140','Novo Hamburgo','RS',5)
,(6,'Rua 104A','261','casa','Acaracuzinho','61920140','Maracanaú','CE',6)
,(7,'Quadra Cinco','428','ap 406','Quarenta Horas (Coqueiro)','67120865','Ananindeua','PA',7)
,(8,'Rua Mogno','401','ap 202','Nova Brasília','76908604','Ji-Paraná','RO',8)
,(9,'Rua Mansueto Vanz','608','','Colégio Agrícola','99714350','Erechim','RS',9)
,(10,'Travessa Esmeralda','163','','Floresta Sul','69912311','Rio Branco','AC',10)
,(11,'Rua Murici','593','casa','Beira Rio','68460042','Castanhal','PA',11)
,(12,'Residencial Dele e Dela','144','','Imperador','68744464','Castanhal','PA',12)
,(13,'Rua Cyro Soares','698','','Jardim Nova Ipanema','18071015','Sorocaba','SP',13)
,(14,'Avenida Poeta Renato Caldas','946','ap 57','Lagoa Azul','59139450','Natal','RN',14)
,(15,'Setor Universitário','348','casa','Setor Universitário','75909290','Rio Verde','MG',15)
,(16,'Rua Júpiter','630','','Nova Floresta','76807088','Porto Velho','RO',16)
,(17,'Rua Ipiau','447','','Novo Aleixo','69099715','Manaus','MG',17)
,(18,'Rua Ave-lira','205','casa','Vila Nova','86707060','Arapongas','PR',18)
,(19,'Rua Savigny','837','ap 89','Barreirinha','82700110','Curitiba','PR',19)
,(20,'Rua João Branco','417','','Fátima','60415020','Fortaleza','CE',20)
,(21,'Rua São Paulo','236','casa','Vila Luizão','65068641','São Luís','MA',21)
,(22,'Avenida Marcelo Gama','127','','Santa Helena','96503798','Cachoeira  do Sul','RS',22)
,(23,'Rua José Pequeno','963','','Cruzeiro','58415635','Campina Grande','PB',23)
,(24,'Rua Bicanga','473','','Vista da Serra I','29176383','Serra','ES',24)
,(25,'Travessa WE-08','226','','Coqueiro','66670355','Belém','PA',25)
,(26,'Rua Itália','373','','Pedrinhas','76801566','Porto Velho','RO',26)
,(27,'Rua F','878','','Cidade Nova','49070796','Aracaju','SE',27)
,(28,'Rua D','976','','Várzea','56912050','Serra Talhada','PE',28)
,(29,'Beco São Pedro','976','','Costa Barros','21512340','Rio de Janeiro','RJ',29)
,(30,'Praça Guedes Miranda','301','','Ponta Grossa','57014320','Maceió','AL',30)
,(31,'Rua Borborema','554','','Jardim Aeroporto','14078450','Ribeirão Preto','SP',31)
,(32,'Rua G','626','','Lagoa de Carapebus','29164584','Serra','ES',32);

INSERT INTO `users` VALUES
 (1,'admin','$2a$10$hlUaPFEckFJCWKH9rLnuMuhldK3PVisBFJHmZ7H88hKCOpYlm7UIa',NULL,'A')
,(2,'rodrigolevirafaelnunes..rodrigolevirafaelnunes@3dmaker.com.br','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',1,'U')
,(3,'esterclarasophieassis-75@salvagninigroup.com','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',2,'U')
,(4,'gaelrodrigoenzobarbosa_@viavaleseguros.com.br','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',3,'U')
,(5,'robertobernardocastro__robertobernardocastro@ozsurfing.com.br','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',4,'U')
,(6,'emilyceciliavieira__emilyceciliavieira@quarttus.com.br','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',5,'U')
,(7,'nicolepatriciasandradaconceicao__nicolepatriciasandradaconceicao@hormail.com','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',6,'U')
,(8,'enzoruandaniloduarte__enzoruandaniloduarte@sistectecnologia.com.br','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',7,'U')
,(9,'carolinegabriellylunaduarte__carolinegabriellylunaduarte@p4ed.com','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',8,'U')
,(10,'sophiecarladamata__sophiecarladamata@salera.com.br','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',9,'U')
,(11,'paulohenriquepietrocampos-70@facebook.com','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',10,'U')
,(12,'iisabeldanielaflaviadias@msaengenharia.com.br','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',11,'U')
,(13,'tiagorenanribeiro..tiagorenanribeiro@tribunadeindaia.com.br','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',12,'U')
,(14,'elisagiovanaisadora@tribunadeindaia.com.br','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',13,'U')
,(15,'jenniferesthersarapeixoto..jenniferesthersarapeixoto@mrv.com.br','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',14,'U')
,(16,'bernardojuanalmada_@graficajardim.com.br','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',15,'u')
,(17,'viniciusfernandorodrigoalves..viniciusfernandorodrigoalves@eton.com.br','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',16,'U')
,(18,'otavioaugustogalvao_@juliosimoes.com.br','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',17,'U')
,(19,'ssilvanaaylamonteiro@dye.com.br','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',18,'U')
,(20,'ccamilalaviniafigueiredo@semco.com.br','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',19,'U')
,(21,'pietrobenjaminbentoalves__pietrobenjaminbentoalves@pronta.com.br','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',20,'U')
,(22,'reginacarolinamaludamata-78@ceuazul.ind.br','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',21,'U')
,(23,'elaineelianeramos..elaineelianeramos@mabeitex.com.br','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',22,'U')
,(24,'isaacigorpeixoto_@bucaneiro.com.br','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',23,'U')
,(25,'rrosangelamilenaluciacardoso@cdcd.com.br','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',24,'U')
,(26,'hhenriqueanthonyerickribeiro@psq.med.br','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',25,'U')
,(27,'yyuriigorduarte@kuehne-nagel.com','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',26,'U')
,(28,'felipeemanuelgaelbrito_@moppe.com.br','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',27,'U')
,(29,'benicioricardodarosa..benicioricardodarosa@focustg.com.br','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',28,'U')
,(30,'henriquejoaomatheusferreira..henriquejoaomatheusferreira@corpus.com.br','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',29,'U')
,(31,'ssuelieloacatarinafarias@cafefrossard.com','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',30,'U')
,(32,'rosangelaemilymirellaramos..rosangelaemilymirellaramos@terapeutaholistica.com.br','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',31,'U')
,(33,'cristianeeduardamendes-84@transmazza.com.br','$2a$10$rDqF.wg03v5rCe8cJQ3m.ujKafZsgRttjsrs.uVXVKPaGOmzJhaoe',32,'U');

INSERT INTO `accounts` VALUES
(1, 1, 1, '9', '0.00', 'CA', 'A', 1),
(2, 1, 2, '7', '0.00', 'CA', 'A', 2),
(3, 1, 3, '5', '0.00', 'CA', 'A', 3),
(4, 1, 4, '3', '0.00', 'CA', 'A', 4),
(5, 1, 5, '1', '0.00', 'CA', 'A', 5),
(6, 1, 6, '1', '0.00', 'CA', 'A', 6),
(7, 1, 7, '8', '0.00', 'CA', 'A', 7),
(8, 1, 8, '6', '0.00', 'CA', 'A', 8),
(9, 1, 9, '4', '0.00', 'CA', 'A', 9),
(10, 1, 10, '8', '0.00', 'CA', 'A', 10),
(11, 1, 1, '9', '0.00', 'SA', 'A', 11),
(12, 1, 2, '7', '0.00', 'SA', 'A', 12),
(13, 1, 3, '5', '0.00', 'SA', 'A', 13),
(14, 1, 4, '3', '0.00', 'SA', 'A', 14),
(15, 1, 5, '1', '0.00', 'SA', 'A', 15),
(16, 1, 6, '1', '0.00', 'SA', 'A', 16),
(17, 1, 7, '8', '0.00', 'SA', 'A', 17),
(18, 1, 8, '6', '0.00', 'SA', 'A', 18),
(19, 1, 9, '4', '0.00', 'SA', 'A', 19),
(20, 1, 10, '8', '0.00', 'SA', 'A', 20);