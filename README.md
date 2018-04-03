# NMT ACM Webapp Demonstration

## Abstract

This repo contains source files for the web application demonstration given at the ACM chapter of NMT. Topics I plan to cover during the demonstration are listed below:
- MVC Architecture
- JSP/HTML
- Java Servlets
- Java Beans
- SQL Databases
- PhpMyAdmin
- Basic Password Encription

## Backend Setup Resources

Since the demonstration time is only an hour, I won't be able to cover how to create the backend of the application. Below are links to resource material on the setup of the backend portion of the web application. I've also taken screen shots and explained the steps I used to create the backend. All of the backend is hosted by AWS (Amazon Web Services). You must first create an account with AWS to use it, however this is not ellaborated on in the walkthrough.

### Links

- [EC2 Console (us-east-1)](https://console.aws.amazon.com/ec2/v2/home?region=us-east-1)
- [RDS Console (us-east-1)](https://console.aws.amazon.com/rds/home?region=us-east-1)
- Tutorial: [Creating EC2 and RDS instances for phpMyAdmin](https://www.youtube.com/watch?v=Bz-4wTGD2_Q)

### Backend Walkthrough

#### Setting Up EC2

EC2 is a server creation service we will use to launch a phpMyAdmin interface. To get started, open the AWS Console via the following link: [https://console.aws.amazon.com/ec2/home?region=us-east-1](https://console.aws.amazon.com/ec2/home?region=us-east-1). This console is the basic interface for AWS EC2 managment. Notice that there is a place to change where on Earth the services are hosted from in the top right corner as a drop-down menu. I've selected North Virgina (corresponding to us-east-1) since it is an option built into Netbeans cloud services.

<<<insert image>>>
  
The console will load all of your EC2 instance information (if you are running any instances). Click on the "Launch Instance" button, then proceed with the following steps:

- On the following page, press "Select" on the Ubuntu Server 16.04 LTS (HVM), SSD Volume Type option. You may also select the checkbox near the top of the page that will only show Free Tier options.
- Under the instance types, choose the t2.micro instance (which is Free Tier eligible) and click the "Review and Launch" button. This will skip the remaining steps, however feel free to go through them and fine-tune the options; under section 5 you can tag the instance with a name, however we can also do this later.
- Select the "Launch" button.
- You will need to create and/or use a generated key pair to access your instance. If you create one, *you will not have access to the private key once you download it from EC2! Be sure to store it in a safe location.*
- Now select "Launch Instances", and the EC2 instance will be launched!

If you go to your launched EC2 instances, you should see a new instance (if it does not have a name, you can hover over that section and select to edit it. I gave mine "phpMyAdminACM" since the server will host phpMyAdmin for us).

<<<insert image>>>

#### Setting Up RDS

RDS is the database service we will use to store and retreive data. Select "Instances" from the menu on the left side to bring up all of your RDS instances. To get started, open the AWS Console via the following link: [https://console.aws.amazon.com/rds/home?region=us-east-1](https://console.aws.amazon.com/rds/home?region=us-east-1). This console is the basic interface for AWS RDS managment. Again, notice that there is a place to change where on Earth the services are hosted from in the top right corner as a drop-down menu. I've selected North Virgina (corresponding to us-east-1) since it is an option built into Netbeans cloud services.

<<<insert image>>>

Before launching an instance, we first want to setup a Subnet Group so that the instance will be able to talk to our EC2 server. Select "Subnet groups" from the menu on the left-hand side. Click the "Create DB Subnet Group" button on the resulting page, and then proceed with the following steps:

- Give the subnet a name and a description. Something along the lines of phpMyAdmin (or more specific if you think you'll launch multiple phpMyAdmin servers) would work well.
- The VPC should be selected, however you can change it if you've created any others
- Click the "Add all subnets related to this VPC" button, and then select "Create".

This should create for you a new subnet group with the name you specified. Now we can continue on to create the RDS DB instance.

Select "Launch DB Instance", then proceed with the following steps:

- Click on MariaDB as the engine type, then click "Next".
- From the different types of MariaDB engines, choose Dev/Test. This is because we want to be within the Free Tier limitations.
- Under "DB Instance Class" select from the dropdown menu "db.t2.micro". This is, again, by Free Tier limitations. If possible, check the box under the popup disclaimer for Free Tier, as shown below. Fill in the bottom text boxes with appropriate names/passwords, and leave the rest of the settings on this page as is.

<<<insert image>>>

- Leave the default settings on the next page except for the "Database name" section; give the database instance a name.
- Click "Launch DB Instance" to finish creating your RDS instance!

After performing the above steps, you should have a working Free Tier RDS instance, like the ones shown below.

<<<insert image>>>

Before closing out this page, click on the RDS instance link and scroll down to the "Connect" section. Copy the link labeled as the "Endpoint" and paste it somewhere useful, we will need it later on.

<<<insert image>>>

### Connecting EC2 and RDS

Now that we have all the ground-work laid, we need to install phpMyAdmin on our EC2 server and connect it to the RDS database. To do this, you must first SSH into the EC2 instance. Since I'm on Windows I use PuTTY, and my process is described below.

- Open the instances page under the EC2 console.
- Select your instance, and then copy the Public DNS (IPv4) information, as highlighted below.

<<<insert image>>>

- Open PuTTY. In the "Host Name (or IP Address)" box, type "ubuntu@" and then paste the copied address. ubuntu is the defualt username for EC2 Ubuntu servers. You can also save this information for easy access.
- Under the "Category" list on the left side of PuTTY, expand "SSH" and click "Auth".
- Click the "Browse" button, and select the private key that you created along with your EC2 instance. It's customary to save this key under ~/.ssh, however this is not always necessary. *You may have to convert the file you downloaded to PEM format in order to use it.*

<<<insert image>>>

- Now select "Open" to launch the connection. You should be logged into your EC2 instance.

<<<insert image>>>

Taken from [andrewpuch/phpmyadmin_connect_to_rds](https://github.com/andrewpuch/phpmyadmin_connect_to_rds/blob/master/README.md), the instructions for installing phpMyAdmin on the EC2 server (once SSH'd inside) are reproduced below. Run all the commands listed, and edit the files as described.

You can copy the following commands as a block and run them in the terminal.

```
sudo su
apt-get update
apt-get upgrade -y
apt-get dist-upgrade -y
apt-get autoremove -y
```
Now we must setup the phpMyAdmin service. Once the following command is run, you will be prompted with an interface that will walk you through setting up phpMyAdmin.

```
apt-get install apache2 php7.0 php7.0-cli php7.0-fpm php7.0-gd php-ssh2 libapache2-mod-php7.0 php7.0-mcrypt mysql-server php7.0-mysql git unzip zip postfix php7.0-curl mailutils php7.0-json phpmyadmin -y
```
During setup, ensure the following.

- Choose a password for the MySQL root user.
- Select "apache2" for the default configuration server.
- Click "yes" when prompted for DB configuration.
- Choose a password for the phpMyAdmin root user on the resulting page. This username is automatically set to "phpmyadmin".

Now that phpMyAdmin is setup, you *should* be able to log into it using the IPv4 Public IP address found under the public DNS address we used before. If you copy that IP address into a browser followed by "/phpMyAdmin" you will be taken to the phpMyAdmin interface.

If this link doesn't work, you should check your Security Groups under the EC2 console. Select the launch-wizard-1 security group (that's what mine was named, at least) with the proper description and click "Edit". You should add a rule, as shown below, that works for All TCP and allows connections from Anywhere.

<<<insert image>>>
  
You should now be able to access the phpMyAdmin interface and login with the phpMyAdmin password you created before. The RDS instance won't be connected, however, so continue with the rest of these commands/instructions to connect EC2 with RDS.

```
phpenmod mcrypt

vim /etc/apache2/sites-enabled/000-default.conf
--ADD LINE AT THE TOP-- 
Include /etc/phpmyadmin/apache.conf

service apache2 restart

vim /etc/phpmyadmin/config.inc.php
--ADD LINES BELOW THE PMA CONFIG AREA (LINE 109) AND FILL IN DETAILS--
$i++;
$cfg['Servers'][$i]['host']          = '__FILL_IN_DETAILS__';
$cfg['Servers'][$i]['port']          = '3306';
$cfg['Servers'][$i]['socket']        = '';
$cfg['Servers'][$i]['connect_type']  = 'tcp';
$cfg['Servers'][$i]['extension']     = 'mysql';
$cfg['Servers'][$i]['compress']      = FALSE;
$cfg['Servers'][$i]['auth_type']     = 'config';
$cfg['Servers'][$i]['user']          = '__FILL_IN_DETAILS__';
$cfg['Servers'][$i]['password']      = '__FILL_IN_DETAILS__';
```

The host will be the RDS endpoint, which is the link we set aside earlier (or you can get it from the instance information in the RDS console). The user and password information comes from when we configured the DB connection during the installation of phpMyAdmin. Once this information is saved, we will have the RDS DB accessible from phpMyAdmin via the dropdown, as demonstrated below.

<<<insert image>>>

