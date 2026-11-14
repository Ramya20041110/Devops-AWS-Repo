# DEVOPS CI/CD PIPELINE WITH JENKINS, DOCKER, ECS, ECR, AND TERRAFORM 
## Project Overview
 This project demonstrates the implementation of an automated CI/CD pipeline for deploying a Python Flask application on AWS Elastic Container Service.
 Terraform is used to provision AWS infrastructure, while Jenkins automates application build, testing, Docker image creation, image publishing to Amazon ECR and deployment to Amazon ECS.
 
 Technologies Used: Terraform, AWS (EC2, ECR, ECS, IAM, RDS), Jenkins, Docker. 

 ## Architecture Diagram
 
 <img width="833" height="557" alt="image" src="https://github.com/user-attachments/assets/b2a01cd0-0bbc-4fb0-89f6-c006f9db9445" />

## Project Implementation Details

AWS infrastructure resources such as EC2, ECS, ECR, and RDS are provisioned using Terraform.

Application source code is maintained in a local Git repository.Whenever code changes are committed and pushed to the GitHub repository, a webhook triggers the Jenkins pipeline.

<img width="940" height="473" alt="image" src="https://github.com/user-attachments/assets/d1965cbe-c568-4812-a9aa-40c5371325e6" />

<img width="940" height="476" alt="image" src="https://github.com/user-attachments/assets/ff01da09-17ef-4e4c-a352-4cc0be7ba99c" />

Jenkins pulls the latest source code from the GitHub repository.The Docker image is built using the Dockerfile available in the source code repository.

<img width="940" height="487" alt="image" src="https://github.com/user-attachments/assets/98b77e20-dd18-4f51-8ef9-0cf9cac9e505" />

<img width="940" height="472" alt="image" src="https://github.com/user-attachments/assets/b71e8a8a-a5de-4346-9adf-7f82db6cf181" />

 The Docker image is pushed to a private repository in Amazon Elastic Container Registry (ECR) for secure storage and management.

 <img width="940" height="475" alt="image" src="https://github.com/user-attachments/assets/33f0e230-9bdb-42fc-be2a-b81494231379" />

The containerized application is deployed using Amazon Elastic Container Service (ECS). The ECS service runs the application container, making the application accessible externally through its public IP address.

<img width="940" height="448" alt="image" src="https://github.com/user-attachments/assets/73f60ef5-41a9-4d0f-929d-b07183a43173" />

<img width="940" height="472" alt="image" src="https://github.com/user-attachments/assets/13a874bc-27c1-446b-b2db-ab24164313de" />

<img width="940" height="478" alt="image" src="https://github.com/user-attachments/assets/315a1824-f2a2-4309-942c-e3dec8c08f78" />

<img width="940" height="479" alt="image" src="https://github.com/user-attachments/assets/1cb4f60d-f77f-452d-98cb-5113d4147391" />

<img width="940" height="474" alt="image" src="https://github.com/user-attachments/assets/f4bab1d1-b72b-4ac1-b45f-a0048dbb1794" />

Amazon Relational Database Service (RDS) is configured to host the database for the Python Flask application.
The Flask application connects to the RDS database using its database endpoint, port, database name, and authentication credentials.
The application accesses and manages database records through this connection.

<img width="940" height="446" alt="image" src="https://github.com/user-attachments/assets/58f9ed13-cc13-43f3-ad0d-e78e0fc07b60" />

<img width="940" height="603" alt="image" src="https://github.com/user-attachments/assets/f7b2da43-8dfd-4a96-ad94-0d8df223d1f7" />

<img width="940" height="529" alt="image" src="https://github.com/user-attachments/assets/e4085d37-f580-477f-84ab-5b7b3d491e44" />

AWS Identity and Access Management (IAM) roles and policies are configured to provide the required permissions to AWS services and control access to AWS resources.

<img width="940" height="472" alt="image" src="https://github.com/user-attachments/assets/4a7ebe2f-7b48-4666-935a-859f4edee528" />

<img width="940" height="475" alt="image" src="https://github.com/user-attachments/assets/17f7c2d8-60a9-4357-a5d0-8696e474c99c" />














