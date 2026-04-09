SUMMARY = "Create custom user with SSH access"
LICENSE = "CLOSED"


SRC_URI += "file://authorized-key.pub"

S = "${WORKDIR}"

inherit useradd

USERADD_PACKAGES = "${PN}"
# Generated with: openssl passwd -5 'your password here'
PI_PASSWORD_HASH='\$5\$Ua3PQ2CV.525K0re\$7oDgdGsQY5qQIaXNszkmHK8i9R4AIGB/yQ8rfYzwnQ6'
USERADD_PARAM:${PN} = "-U -d /home/pi -s /bin/sh -p '${PI_PASSWORD_HASH}' pi"

do_install() {
    install -d -m 0700 ${D}/home/root/.ssh
    install -m 0600 ${WORKDIR}/authorized-key.pub ${D}/home/root/.ssh/authorized_keys
    # chown -R pi:pi ${D}/home/pi ${D}/home/pi/.ssh ${D}/home/pi/.ssh/authorized_keys
}


FILES:${PN} += "/home/root/.ssh /home/root/.ssh/authorized_keys"