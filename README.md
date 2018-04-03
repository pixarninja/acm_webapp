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
- Tutorial: [Creating an EC2 instance]()
- Tutorial: [Creating an RDS instance and linking to EC2](https://www.youtube.com/watch?v=I9Fzm1obG7U)

### Backend Walkthrough

#### Setting Up EC2

EC2 is a server creation service we will use to launch a phpMyAdmin interface. To get started, open the "Services" dropdown menu from the home console page, and select EC2 from the list.

<<<insert image>>>
  
The resulting page will load all of your EC2 instance information (if you are running any instances). Click on the "Launch Instance" button, then proceed with the following steps:

- On the following page, press "Select" on the Ubuntu Server 16.04 LTS (HVM), SSD Volume Type option. You may also select the checkbox near the top of the page that will only show Free Tier options.
- Under the instance types, choose the t2.micro instance (which is Free Tier eligible) and click the "Review and Launch" button. This will skip the remaining steps, however feel free to go through them and fine-tune the options; under section 5 you can tag the instance with a name, however we can also do this later.
- Select the "Launch" button.
- You will need to create and/or use a generated key pair to access your instance. If you create one, *you will not have access to the private key once you download it from EC2! Be sure to store it in a safe location.*
- Now select "Launch Instances", and the EC2 instance will be launched!

If you go to your launched EC2 instances, you should see a new instance (if it does not have a name, you can hover over that section and select to edit it. I gave mine "phpMyAdminACM" since the server will host phpMyAdmin for us).

<<<insert image>>>

#### Setting Up RDS

RDS is the database service we will use to store and retreive data. Select "Instances" from the menu on the left side to bring up all of your RDS instances. To get started, open the AWS Console via the following link: [https://console.aws.amazon.com/rds/home?region=us-east-1](https://console.aws.amazon.com/rds/home?region=us-east-1). This console is the basic interface for AWS RDS managment. Notice that there is a place to change where on Earth the services are hosted from in the top right corner as a drop-down menu. I've selected North Virgina (corresponding to us-east-1) since it is an option built into Netbeans cloud services.

<<<insert image>>>

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
