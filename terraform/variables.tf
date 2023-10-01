variable "ansible_ssh_pass" {
  default = "Passw0rd"
}

variable "ansible_command" {
  default     = "../playbook/kubeadm-shell.yml"
  description = "Command for container lab hosts"
}

variable "ansible_user" {
  type        = string
  description = "Ansible user used to connect to the instance"
  default     = "ubuntu"
}

variable "ansible_ssh_private_key_file" {
  type        = string
  description = "ssh key file to use for ansible_user"
  default     = "./terragrunt-eks.pem"
}

variable "ansible_ssh_public_key_file" {
  type        = string
  description = "ssh public key in server authorized_keys"
  default     = "terragrunt-eks"
}

variable "ansible_python" {
  type        = string
  description = "path to python executable"
  default     = "/usr/bin/python3"
}

variable "instance_type" {
  default = "c7g.2xlarge"
}