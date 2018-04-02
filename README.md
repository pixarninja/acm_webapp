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

- [AWS: Creating RDS and EC2 Instances](https://www.youtube.com/watch?v=I9Fzm1obG7U)

### Walkthrough

#setting-up-ec2

#### Setting Up RDS

Open the AWS Console via the following link: [https://console.aws.amazon.com/console/home?region=us-east-1#](https://console.aws.amazon.com/console/home?region=us-east-1#). This console is the basic interface for AWS. Notice that there is a place to change where on Earth the services are hosted from in the top right corner as a drop-down menu. I've selected North Virgina (corresponding to us-east-1) since it is an option built into Netbeans cloud services.

Next, open the "Services" dropdown menu and select RDS from the list. RDS is the database service we will use to store and retreive data. Select "Instances" from the menu on the left side to bring up all of your RDS instances.

<<<insert image>>>

Select "Launch DB Instance", then proceed with the following steps:

- Click on MariaDB as the engine type, then click "Next".
- From the different types of MariaDB engines, choose Dev/Test. This is because we want to be within the Free Tier limitations.
- Under "DB Instance Class" select from the dropdown menu "db.t2.micro". This is, again, by Free Tier limitations. If possible, check the box under the popup disclaimer for Free Tier, as shown below. Fill in the bottom text boxes with appropriate names/passwords, and leave the rest of the settings on this page as is.

<<<insert image>>>

- Leave the default settings on the next page except for the "Database name" section; give the database instance a name.
- Click "Launch DB Instance" to finish creating your RDS instance!

#### Setting Up EC2

Open the "Services" dropdown menu from the home console page, and select EC2 from the list.

<<<insert image here>>>
  
The resulting page will load all of your EC2 instance information (if you are running any instances).  
