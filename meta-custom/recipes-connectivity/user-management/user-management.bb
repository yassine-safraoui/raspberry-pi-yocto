SUMMARY = "Create custom user with SSH access"
LICENSE = "CLOSED"


SRC_URI += "file://authorized-key.pub"

S = "${WORKDIR}"


do_install() {
    install -d -m 0700 ${D}/home/pi/.ssh
    install -m 0600 ${WORKDIR}/authorized-key.pub ${D}/home/pi/.ssh/authorized_keys
    # chown -R 1000:1000 ${D}/home/pi ${D}/home/pi/.ssh ${D}/home/pi/.ssh/authorized_keys
}

# Doesn't work, build succeeds but files' owner don't change when checking at runtime
# ROOTFS_POSTPROCESS_COMMAND += "fix_pi_ssh_perms; "
# fix_pi_ssh_perms() {
#     chown -R 1000:1000 ${D}/home/pi ${D}/home/pi/.ssh ${D}/home/pi/.ssh/authorized_keys
# }


FILES:${PN} += "/home/pi/.ssh /home/pi/.ssh/authorized_keys"