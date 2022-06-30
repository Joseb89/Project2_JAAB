terraform {
  required_providers {
    google = {
      source = "hashicorp/google"
      version = "~> 4.0"
    }
  }
}

provider "google" {
  credentials = file("C:\\Users\\jbarr\\Downloads\\project2-jaab-key.json")
  project = "project2-jaab"
  region = "us-central1"
  zone = "us-central1-a"
}

resource "google_compute_address" "static" {
  name = "project2-publicip"
}

resource "google_compute_instance" "vm-instance"  {

  name         = "project2-vm"
  machine_type = "e2-micro"
  tags = ["allow-http"]

  boot_disk {
    initialize_params {
      image = "ubuntu-os-cloud/ubuntu-2204-lts"
      size = 10
    }
  }

  network_interface {
    network = "default"

    access_config {
      nat_ip = google_compute_address.static.address
    }
  }
}

resource "google_compute_firewall" "allow_http" {
  name    = "allow-http-rule"
  network = "default"

  allow {
    ports = ["80"]
    protocol = "tcp"
  }

  source_tags = ["allow-http"]

  priority = 1000
}

output "public_id_address" {
  value = google_compute_address.static.address
}
