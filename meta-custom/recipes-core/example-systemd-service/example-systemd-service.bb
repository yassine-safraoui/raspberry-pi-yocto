SUMMARY = "Example systemd service unit"
LICENSE = "CLOSED"

inherit systemd

SRC_URI = "file://example-systemd-service.service"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/example-systemd-service.service \
        ${D}${systemd_system_unitdir}/example-systemd-service.service
}

SYSTEMD_SERVICE:${PN} = "example-systemd-service.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

FILES:${PN} += "${systemd_system_unitdir}/example-systemd-service.service"
