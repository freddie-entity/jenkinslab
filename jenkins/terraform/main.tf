resource "aws_instance" "controlplane" {
  ami           = data.aws_ami.ubuntu.id
  instance_type = var.instance_type
  count         = 1
  key_name      = var.ansible_ssh_public_key_file
  # user_data     = file("${path.module}/scripts/container.sh")

  tags = {
    Name = "CICD Server"
  }
}