resource "local_file" "ansible_inventory" {
  filename = "./ansible/inventory/lab"
  content  = <<-EOT
    [controlplane]
    %{for ip in aws_instance.controlplane.*.public_ip~} 
    ${ip} ansible_host=${ip} ansible_user=${var.ansible_user} ansible_ssh_private_key_file=${var.ansible_ssh_private_key_file} ansible_ssh_common_args='-o StrictHostKeyChecking=no' ansible_ssh_connection=ssh 
    %{endfor~}
  EOT
}

resource "null_resource" "playbook_exec" {
  triggers = {
    key = uuid()
  }

  provisioner "local-exec" {
    command = <<EOF
      ansible-playbook ${var.ansible_command} -i ./ansible/inventory/lab
      EOF
  }
  depends_on = [aws_instance.controlplane, local_file.ansible_inventory]
}