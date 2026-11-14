provider "aws" {
  region = "ap-south-1"
}
resource "aws_instance" "master-node" {
  ami           = "ami-094adff9b24048c5c"
  instance_type = "c7i-flex.large"
  key_name = "linapp"
  vpc_security_group_ids = ["sg-0287a28be8496e092"]
  associate_public_ip_address = true
    
    root_block_device {
        volume_size = 30
        volume_type = "gp3"
        encrypted   = true
    }
    tags = {
        Name = "ubun-inst"
    }
}

resource "aws_ecr_repository" "master-registry" {
  name                 = "student-data"
  image_tag_mutability = "MUTABLE"

  image_scanning_configuration {
    scan_on_push = true
  }
  encryption_configuration {
    encryption_type = "AES256"
  }

}

resource "aws_ecs_cluster" "deploy" {
  name = "deployment"

  setting {
    name  = "containerInsights"
    value = "enabled"
  }
}
data "aws_subnets" "default" {
  filter {
    name   = "vpc-id"
    values = ["vpc-00d4417dfccbb8c77"]
  }

  filter {
    name   = "default-for-az"
    values = ["true"]
  }
}


resource "aws_db_subnet_group" "rds-subG" {
  name       = "devsecops-db-subnet-group"
  subnet_ids = data.aws_subnets.default.ids

  tags = {
    Name = "devsecops-db-subnet-group"
  }
}


resource "aws_db_instance" "dev-rds" {
  allocated_storage    = 20
  db_name              = "devopspro123"
  engine               = "mysql"
  engine_version       = "8.0"
  instance_class       = "db.t3.micro"
  username             = "admin"
  password             = "AdminUser5564"
  parameter_group_name = "default.mysql8.0"
  skip_final_snapshot  = true
  vpc_security_group_ids = ["sg-0287a28be8496e092"] 
  publicly_accessible = false
}


