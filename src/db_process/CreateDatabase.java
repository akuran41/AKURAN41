package db_process;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import files.ReadMyIniFile;

public class CreateDatabase
{
	private ConnectDatabase	connectDatabase;
	private Connection		connection	= null;

	private String			data		= null;

	public CreateDatabase() throws SQLException
	{
		readIniFile();

		connectDatabase = new ConnectDatabase();

		if (connectDatabase.isConnected())
		{
			connection = connectDatabase.getMysqlConnection();

			// Ilk once Bos bir veri tabani olusturalim
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + data + " DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;");

			// Statement kapat
			if (stmt != null)
				stmt.close();

			// Daha sonra Tablolari olusturalim
			createTables();
			// Varsayilan verileri yerlestirelim
			generateRecords();
		}
	}

	private void createTables() throws SQLException
	{
		String[] tableNames = {
				"CREATE TABLE bitki (_id int(6) UNSIGNED NOT NULL, bitki_adi varchar(120) NOT NULL, bitki_cinsi varchar(120) NOT NULL, bitki_turu enum('YAPRAK','KÖK','SEBZE','MEYVE','ÇİÇEK') NOT NULL DEFAULT 'YAPRAK', bitki_resim varchar(120) DEFAULT NULL, bitki_ulke varchar(50) NOT NULL, bitki_yore varchar(120) NOT NULL, bitki_ekme_zamani date NOT NULL DEFAULT '0000-00-00', bitki_yetisme_suresi int(4) NOT NULL, bitki_hasat_zamani date NOT NULL DEFAULT '0000-00-00', bitki_tohum_satici varchar(120) DEFAULT NULL, bitki_tohum_fiyat float DEFAULT NULL, bitki_fide_satici varchar(120) DEFAULT NULL, bitki_fide_fiyat float DEFAULT NULL, bitki_hasat_boy int(5) DEFAULT NULL, bitki_hasat_agirlik int(6) DEFAULT NULL, bitki_hasat_fiyat float DEFAULT NULL, bitki_satis_tip varchar(15) DEFAULT NULL DEFAULT 'ADET', bitki_dikim_tip varchar(15) DEFAULT NULL DEFAULT 'TOHUM', bitki_isik_siddet varchar(10) DEFAULT NULL, bitki_isik_dalgaboy varchar(6) DEFAULT NULL, bitki_gunduz_isik_sure int(2) DEFAULT NULL, bitki_gece_isik_sure int(2) DEFAULT NULL, bitki_gunduz_ortam_isi float DEFAULT NULL, bitki_gece_ortam_isi float DEFAULT NULL, bitki_gunduz_nem float DEFAULT NULL, bitki_gece_nem float DEFAULT NULL, bitki_gunduz_o2 int(3) DEFAULT NULL, bitki_gece_o2 int(3) DEFAULT NULL, bitki_gunduz_c2o int(3) DEFAULT NULL, bitki_gece_c2o int(3) DEFAULT NULL, bitki_gunduz_cansuyu_isi float DEFAULT NULL, bitki_gece_cansuyu_isi float DEFAULT NULL, bitki_cansuyu_ph varchar(5) DEFAULT NULL, bitki_gunduz_gida_a varchar(55) DEFAULT NULL, bitki_gunduz_gida_b varchar(55) DEFAULT NULL, bitki_gunduz_gida_c varchar(55) DEFAULT NULL, bitki_gunduz_gida_d varchar(55) DEFAULT NULL, bitki_gunduz_gida_e varchar(55) DEFAULT NULL, bitki_gunduz_gida_f varchar(55) DEFAULT NULL, bitki_gece_gida_a varchar(55) DEFAULT NULL, bitki_gece_gida_b varchar(55) DEFAULT NULL, bitki_gece_gida_c varchar(55) DEFAULT NULL, bitki_gece_gida_d varchar(55) DEFAULT NULL, bitki_gece_gida_e varchar(55) DEFAULT NULL, bitki_gece_gida_f varchar(55) DEFAULT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;",
				"CREATE TABLE countries (_id int(3) UNSIGNED NOT NULL, country_code varchar(2) NOT NULL, country_name varchar(100) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;",
				"CREATE TABLE hall (_id int(3) UNSIGNED NOT NULL, add_date date NOT NULL DEFAULT 0000-00-00) ENGINE=InnoDB DEFAULT CHARSET=utf8;",
				"CREATE TABLE pic_cards (_id int(5) UNSIGNED NOT NULL, pic_id int(5) NOT NULL, bitki_id int(6) NOT NULL, addDate date NOT NULL DEFAULT '0000-00-00') ENGINE=InnoDB DEFAULT CHARSET=utf8;",
				"CREATE TABLE product_log (_id int(12) UNSIGNED NOT NULL, pic_id int(5) NOT NULL, env_temp int(3) NOT NULL COMMENT 'C°', env_humid int(3) NOT NULL COMMENT 'RH', env_02 int(3) NOT NULL COMMENT '%', env_co2 int(3) NOT NULL COMMENT '%', env_light int(3) NOT NULL COMMENT 'Lux', env_pressure int(3) NOT NULL COMMENT 'BAR', env_water_temp int(3) NOT NULL COMMENT 'C°', env_water_ph float NOT NULL COMMENT 'PH', a_gida varchar(10) NOT NULL COMMENT 'ppm', b_gida varchar(10) NOT NULL COMMENT 'ppm', c_gida varchar(10) NOT NULL COMMENT 'ppm', d_gida varchar(10) NOT NULL COMMENT 'ppm', env_heat_valve int(3) NOT NULL COMMENT '%', env_cool_valve int(3) NOT NULL COMMENT '%', log_time datetime NOT NULL DEFAULT '0000-00-00 00:00:00') ENGINE=InnoDB DEFAULT CHARSET=utf8;",
				"CREATE TABLE sys_log (_id int(10) UNSIGNED NOT NULL, panel_id int(6) NOT NULL, bitki_id int(8) NOT NULL, kayit_araligi int(4) NOT NULL COMMENT 'in minute', dikim_zamani int(10) NOT NULL COMMENT 'unix time', suresi int(10) NOT NULL COMMENT 'unix time', hasat_zamani int(3) NOT NULL COMMENT 'days', env_temp float NOT NULL COMMENT 'ortam sicakligi', env_humid float NOT NULL COMMENT 'ortam nem', env_oxigen float NOT NULL COMMENT 'Ortam Oksijeni', env_co2 float NOT NULL COMMENT 'Ortam Karbondioksit', env_light int(3) NOT NULL COMMENT 'Ortam Isik (Max 100)', env_water_ph float NOT NULL COMMENT 'Can suyu PH degeri', env_water_temp float NOT NULL COMMENT 'Can suyu Sicakligi', a_ingredient int(5) NOT NULL COMMENT 'A G1da katk1 Miktar1 (Mg)', b_ingredient int(5) NOT NULL COMMENT 'B G1da katk1 Miktar1 (Mg)', c_ingredient int(5) NOT NULL COMMENT 'C G1da katk1 Miktar1 (Mg)', d_ingredient int(5) NOT NULL COMMENT 'D G1da katk1 Miktar1 (Mg)', log_time datetime NOT NULL DEFAULT '0000-00-00 00:00:00') ENGINE=InnoDB DEFAULT CHARSET=utf8;",
				"CREATE TABLE user (_id int(4) UNSIGNED NOT NULL, auth_id int(2) NOT NULL, user_name varchar(50) NOT NULL, user_id varchar(15) NOT NULL, user_pass varchar(60) NOT NULL, user_mail varchar(120) NOT NULL, user_phone varchar(22) NOT NULL, user_register datetime NOT NULL DEFAULT '0000-00-00 00:00:00', user_last_login int(1) DEFAULT NULL, isActive enum('1','0') NOT NULL DEFAULT '0') ENGINE=InnoDB DEFAULT CHARSET=utf8;",
				"CREATE TABLE user_auth (_id int(2) UNSIGNED NOT NULL, auth_label varchar(1) NOT NULL, auth_name varchar(25) NOT NULL, auth_read enum('1','0') NOT NULL DEFAULT '0', auth_write enum('1','0') NOT NULL DEFAULT '0', auth_edit enum('1','0') NOT NULL DEFAULT '0', auth_delete enum('1','0') NOT NULL DEFAULT '0', auth_new enum('1','0') NOT NULL DEFAULT '0') ENGINE=InnoDB DEFAULT CHARSET=utf8;",
				"CREATE TABLE user_log (_id int(12) UNSIGNED NOT NULL, user_id int(4) NOT NULL, login_time datetime NOT NULL DEFAULT '0000-00-00 00:00:00', logout_time int(12) DEFAULT '0', user_process text NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;"};
		String[] alterTables = {
				"ALTER TABLE `bitki` ADD PRIMARY KEY (`_id`);",
				"ALTER TABLE `countries` ADD PRIMARY KEY (`_id`);",
				"ALTER TABLE `hall` ADD PRIMARY KEY (`_id`);",
				"ALTER TABLE `pic_cards` ADD PRIMARY KEY (`_id`);",
				"ALTER TABLE `product_log` ADD PRIMARY KEY (`_id`);",
				"ALTER TABLE `sys_log` ADD PRIMARY KEY (`_id`);",
				"ALTER TABLE `user` ADD PRIMARY KEY (`_id`),  ADD UNIQUE KEY `user_id` (`user_id`),  ADD UNIQUE KEY `user_mail` (`user_mail`),  ADD UNIQUE KEY `user_phone` (`user_phone`);",
				"ALTER TABLE `user_auth` ADD PRIMARY KEY (`_id`),  ADD UNIQUE KEY `auth_name` (`auth_name`);",
				"ALTER TABLE `user_log` ADD PRIMARY KEY (`_id`);", "ALTER TABLE `bitki`  MODIFY `_id` int(6) UNSIGNED NOT NULL AUTO_INCREMENT;",
				"ALTER TABLE `countries` MODIFY `_id` int(3) UNSIGNED NOT NULL AUTO_INCREMENT;",
				"ALTER TABLE `hall` MODIFY `_id` int(3) UNSIGNED NOT NULL AUTO_INCREMENT;",
				"ALTER TABLE `pic_cards` MODIFY `_id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;",
				"ALTER TABLE `product_log` MODIFY `_id` int(12) UNSIGNED NOT NULL AUTO_INCREMENT;",
				"ALTER TABLE `sys_log` MODIFY `_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;",
				"ALTER TABLE `user` MODIFY `_id` int(4) UNSIGNED NOT NULL AUTO_INCREMENT;",
				"ALTER TABLE `user_auth` MODIFY `_id` int(2) UNSIGNED NOT NULL AUTO_INCREMENT;",
				"ALTER TABLE `user_log` MODIFY `_id` int(12) UNSIGNED NOT NULL AUTO_INCREMENT;"};

		ResultSet resultSet = connectDatabase.getMysqlConnection().getMetaData().getCatalogs();

		// MySQL icindeki veritbani listesini al
		while (resultSet.next())
		{
			// Eger liste icerisinde SERA isminde veri tabani varsa
			// donguyu sonlandir
			if (resultSet.getString(1).equals(data))
				break;
		}

		connectDatabase = new ConnectDatabase(true);
		connection = connectDatabase.getMysqlConnection();

		Statement queryStatement = null;
		queryStatement = connection.createStatement();

		for (String tables : tableNames)
			queryStatement.executeUpdate(tables);

		for (String alters : alterTables)
			queryStatement.executeUpdate(alters);

		if (queryStatement != null)
			queryStatement.close();
	}

	private void generateRecords() throws SQLException
	{
		String[] defaultRecords = {
				"INSERT INTO `user` (`_id`, `auth_id`, `user_name`, `user_id`, `user_pass`, `user_mail`, `user_phone`, `user_register`, `user_last_login`, `isActive`) VALUES(1, 2, 'admin admin', 'admin', 'admin', 'admin@localhost.com', '000-000-0000', '2006-05-20 19:01:00', 0, '1');",
				"INSERT INTO `user_auth` (`_id`, `auth_label`, `auth_name`, `auth_read`, `auth_write`, `auth_edit`, `auth_delete`, `auth_new`) VALUES(1, 'A', 'Sistem kaydedici', '1', '1', '0', '0', '0'),(2, 'B', 'Administrator', '1', '1', '1', '1', '1'),(3, 'C', 'Mali sorumlu', '1', '1', '1', '0', '0'),(4, 'D', 'Teknik sorumlu', '1', '1', '1', '0', '1'),(5, 'E', 'Yetiştirici', '1', '1', '0', '0', '1'),(6, 'F', 'İzleyici', '1', '0', '0', '0', '0'),(7, 'G', 'Misafir', '1', '0', '0', '0', '0');",
				"INSERT INTO `countries` (`_id`, `country_code`, `country_name`) VALUES(1, 'AF', 'Afghanistan'),(2, 'AL', 'Albania'),(3, 'DZ', 'Algeria'),(4, 'DS', 'American Samoa'),(5, 'AD', 'Andorra'),(6, 'AO', 'Angola'),(7, 'AI', 'Anguilla'),(8, 'AQ', 'Antarctica'),(9, 'AG', 'Antigua and Barbuda'),(10, 'AR', 'Argentina'),(11, 'AM', 'Armenia'),(12, 'AW', 'Aruba'),(13, 'AU', 'Australia'),(14, 'AT', 'Austria'),(15, 'AZ', 'Azerbaijan'),(16, 'BS', 'Bahamas'),(17, 'BH', 'Bahrain'),(18, 'BD', 'Bangladesh'),(19, 'BB', 'Barbados'),(20, 'BY', 'Belarus'),(21, 'BE', 'Belgium'),(22, 'BZ', 'Belize'),(23, 'BJ', 'Benin'),(24, 'BM', 'Bermuda'),(25, 'BT', 'Bhutan'),(26, 'BO', 'Bolivia'),(27, 'BA', 'Bosnia and Herzegovina'),(28, 'BW', 'Botswana'),(29, 'BV', 'Bouvet Island'),(30, 'BR', 'Brazil'),(31, 'IO', 'British Indian Ocean Territory'),(32, 'BN', 'Brunei Darussalam'),(33, 'BG', 'Bulgaria'),(34, 'BF', 'Burkina Faso'),(35, 'BI', 'Burundi'),(36, 'KH', 'Cambodia'),(37, 'CM', 'Cameroon'),(38, 'CA', 'Canada'),(39, 'CV', 'Cape Verde'),(40, 'KY', 'Cayman Islands'),(41, 'CF', 'Central African Republic'),(42, 'TD', 'Chad'),(43, 'CL', 'Chile'),(44, 'CN', 'China'),(45, 'CX', 'Christmas Island'),(46, 'CC', 'Cocos (Keeling) Islands'),(47, 'CO', 'Colombia'),(48, 'KM', 'Comoros'),(49, 'CG', 'Congo'),(50, 'CK', 'Cook Islands'),(51, 'CR', 'Costa Rica'),(52, 'HR', 'Croatia (Hrvatska)'),(53, 'CU', 'Cuba'),(54, 'CY', 'Cyprus'),(55, 'CZ', 'Czech Republic'),(56, 'DK', 'Denmark'),(57, 'DJ', 'Djibouti'),(58, 'DM', 'Dominica'),(59, 'DO', 'Dominican Republic'),(60, 'TP', 'East Timor'),(61, 'EC', 'Ecuador'),(62, 'EG', 'Egypt'),(63, 'SV', 'El Salvador'),(64, 'GQ', 'Equatorial Guinea'),(65, 'ER', 'Eritrea'),(66, 'EE', 'Estonia'),(67, 'ET', 'Ethiopia'),(68, 'FK', 'Falkland Islands (Malvinas)'),(69, 'FO', 'Faroe Islands'),(70, 'FJ', 'Fiji'),(71, 'FI', 'Finland'),(72, 'FR', 'France'),(73, 'FX', 'France, Metropolitan'),(74, 'GF', 'French Guiana'),(75, 'PF', 'French Polynesia'),(76, 'TF', 'French Southern Territories'),(77, 'GA', 'Gabon'),(78, 'GM', 'Gambia'),(79, 'GE', 'Georgia'),(80, 'DE', 'Germany'),(81, 'GH', 'Ghana'),(82, 'GI', 'Gibraltar'),(83, 'GK', 'Guernsey'),(84, 'GR', 'Greece'),(85, 'GL', 'Greenland'),(86, 'GD', 'Grenada'),(87, 'GP', 'Guadeloupe'),(88, 'GU', 'Guam'),(89, 'GT', 'Guatemala'),(90, 'GN', 'Guinea'),(91, 'GW', 'Guinea-Bissau'),(92, 'GY', 'Guyana'),(93, 'HT', 'Haiti'),(94, 'HM', 'Heard and Mc Donald Islands'),(95, 'HN', 'Honduras'),(96, 'HK', 'Hong Kong'),(97, 'HU', 'Hungary'),(98, 'IS', 'Iceland'),(99, 'IN', 'India'),(100, 'IM', 'Isle of Man'),(101, 'ID', 'Indonesia'),(102, 'IR', 'Iran (Islamic Republic of)'),(103, 'IQ', 'Iraq'),(104, 'IE', 'Ireland'),(105, 'IL', 'Israel'),(106, 'IT', 'Italy'),(107, 'CI', 'Ivory Coast'),(108, 'JE', 'Jersey'),(109, 'JM', 'Jamaica'),(110, 'JP', 'Japan'),(111, 'JO', 'Jordan'),(112, 'KZ', 'Kazakhstan'),(113, 'KE', 'Kenya'),(114, 'KI', 'Kiribati'),(115, 'KP', 'Korea, Democratic People''s Republic of'),(116, 'KR', 'Korea, Republic of'),(117, 'XK', 'Kosovo'),(118, 'KW', 'Kuwait'),(119, 'KG', 'Kyrgyzstan'),(120, 'LA', 'Lao People''s Democratic Republic'),(121, 'LV', 'Latvia'),(122, 'LB', 'Lebanon'),(123, 'LS', 'Lesotho'),(124, 'LR', 'Liberia'),(125, 'LY', 'Libyan Arab Jamahiriya'),(126, 'LI', 'Liechtenstein'),(127, 'LT', 'Lithuania'),(128, 'LU', 'Luxembourg'),(129, 'MO', 'Macau'),(130, 'MK', 'Macedonia'),(131, 'MG', 'Madagascar'),(132, 'MW', 'Malawi'),(133, 'MY', 'Malaysia'),(134, 'MV', 'Maldives'),(135, 'ML', 'Mali'),(136, 'MT', 'Malta'),(137, 'MH', 'Marshall Islands'),(138, 'MQ', 'Martinique'),(139, 'MR', 'Mauritania'),(140, 'MU', 'Mauritius'),(141, 'TY', 'Mayotte'),(142, 'MX', 'Mexico'),(143, 'FM', 'Micronesia, Federated States of'),(144, 'MD', 'Moldova, Republic of'),(145, 'MC', 'Monaco'),(146, 'MN', 'Mongolia'),(147, 'ME', 'Montenegro'),(148, 'MS', 'Montserrat'),(149, 'MA', 'Morocco'),(150, 'MZ', 'Mozambique'),(151, 'MM', 'Myanmar'),(152, 'NA', 'Namibia'),(153, 'NR', 'Nauru'),(154, 'NP', 'Nepal'),(155, 'NL', 'Netherlands'),(156, 'AN', 'Netherlands Antilles'),(157, 'NC', 'New Caledonia'),(158, 'NZ', 'New Zealand'),(159, 'NI', 'Nicaragua'),(160, 'NE', 'Niger'),(161, 'NG', 'Nigeria'),(162, 'NU', 'Niue'),(163, 'NF', 'Norfolk Island'),(164, 'MP', 'Northern Mariana Islands'),(165, 'NO', 'Norway'),(166, 'OM', 'Oman'),(167, 'PK', 'Pakistan'),(168, 'PW', 'Palau'),(169, 'PS', 'Palestine'),(170, 'PA', 'Panama'),(171, 'PG', 'Papua New Guinea'),(172, 'PY', 'Paraguay'),(173, 'PE', 'Peru'),(174, 'PH', 'Philippines'),(175, 'PN', 'Pitcairn'),(176, 'PL', 'Poland'),(177, 'PT', 'Portugal'),(178, 'PR', 'Puerto Rico'),(179, 'QA', 'Qatar'),(180, 'RE', 'Reunion'),(181, 'RO', 'Romania'),(182, 'RU', 'Russian Federation'),(183, 'RW', 'Rwanda'),(184, 'KN', 'Saint Kitts and Nevis'),(185, 'LC', 'Saint Lucia'),(186, 'VC', 'Saint Vincent and the Grenadines'),(187, 'WS', 'Samoa'),(188, 'SM', 'San Marino'),(189, 'ST', 'Sao Tome and Principe'),(190, 'SA', 'Saudi Arabia'),(191, 'SN', 'Senegal'),(192, 'RS', 'Serbia'),(193, 'SC', 'Seychelles'),(194, 'SL', 'Sierra Leone'),(195, 'SG', 'Singapore'),(196, 'SK', 'Slovakia'),(197, 'SI', 'Slovenia'),(198, 'SB', 'Solomon Islands'),(199, 'SO', 'Somalia'),(200, 'ZA', 'South Africa'),(201, 'GS', 'South Georgia South Sandwich Islands'),(202, 'ES', 'Spain'),(203, 'LK', 'Sri Lanka'),(204, 'SH', 'St. Helena'),(205, 'PM', 'St. Pierre and Miquelon'),(206, 'SD', 'Sudan'),(207, 'SR', 'Suriname'),(208, 'SJ', 'Svalbard and Jan Mayen Islands'),(209, 'SZ', 'Swaziland'),(210, 'SE', 'Sweden'),(211, 'CH', 'Switzerland'),(212, 'SY', 'Syrian Arab Republic'),(213, 'TW', 'Taiwan'),(214, 'TJ', 'Tajikistan'),(215, 'TZ', 'Tanzania, United Republic of'),(216, 'TH', 'Thailand'),(217, 'TG', 'Togo'),(218, 'TK', 'Tokelau'),(219, 'TO', 'Tonga'),(220, 'TT', 'Trinidad and Tobago'),(221, 'TN', 'Tunisia'),(222, 'TR', 'Turkey'),(223, 'TM', 'Turkmenistan'),(224, 'TC', 'Turks and Caicos Islands'),(225, 'TV', 'Tuvalu'),(226, 'UG', 'Uganda'),(227, 'UA', 'Ukraine'),(228, 'AE', 'United Arab Emirates'),(229, 'GB', 'United Kingdom'),(230, 'US', 'United States'),(231, 'UM', 'United States minor outlying islands'),(232, 'UY', 'Uruguay'),(233, 'UZ', 'Uzbekistan'),(234, 'VU', 'Vanuatu'),(235, 'VA', 'Vatican City State'),(236, 'VE', 'Venezuela'),(237, 'VN', 'Vietnam'),(238, 'VG', 'Virgin Islands (British)'),(239, 'VI', 'Virgin Islands (U.S.)'),(240, 'WF', 'Wallis and Futuna Islands'),(241, 'EH', 'Western Sahara'),(242, 'YE', 'Yemen'),(243, 'YU', 'Yugoslavia'),(244, 'ZR', 'Zaire'),(245, 'ZM', 'Zambia'),(246, 'ZW', 'Zimbabwe');"};

		Statement queryStatement = connection.createStatement();

		for (String query : defaultRecords)
			queryStatement.executeUpdate(query);

		if (queryStatement != null)
			queryStatement.close();
	}

	private void readIniFile()
	{
		ReadMyIniFile readMyIniFile = null;

		try
		{
			readMyIniFile = new ReadMyIniFile();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		data = readMyIniFile.getMyDatabaseName();
	}
}