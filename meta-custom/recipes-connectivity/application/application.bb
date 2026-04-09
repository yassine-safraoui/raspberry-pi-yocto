SUMMARY = "Example systemd service unit"
LICENSE = "CLOSED"

inherit systemd


SRC_URI = "file://application-systemd.service \
           file://main.py    \
    "

S = "${WORKDIR}"

do_install() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/application-systemd.service \
        ${D}${systemd_system_unitdir}/application-systemd.service
    
    install -d ${D}/usr/bin/application
    install -m 0644 ${WORKDIR}/main.py ${D}/usr/bin/application/main.py
}

SYSTEMD_SERVICE:${PN} = "application-systemd.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

FILES:${PN} += "${systemd_system_unitdir}/application-systemd.service \
                ${D}/usr/bin/application/main.py  \
    "
RDEPENDS:${PN} += "python3-bleak"