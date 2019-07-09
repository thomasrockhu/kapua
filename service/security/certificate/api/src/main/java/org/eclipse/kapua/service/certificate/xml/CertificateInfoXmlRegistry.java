/*******************************************************************************
 * Copyright (c) 2017 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.service.certificate.xml;

import org.eclipse.kapua.locator.KapuaLocator;
import org.eclipse.kapua.service.certificate.CertificateUsage;
import org.eclipse.kapua.service.certificate.CertificateInfo;
import org.eclipse.kapua.service.certificate.CertificateInfoCreator;
import org.eclipse.kapua.service.certificate.CertificateInfoFactory;
import org.eclipse.kapua.service.certificate.CertificateInfoListResult;
import org.eclipse.kapua.service.certificate.CertificateInfoQuery;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class CertificateInfoXmlRegistry {

    private static final KapuaLocator LOCATOR = KapuaLocator.getInstance();
    private static final CertificateInfoFactory FACTORY = LOCATOR.getFactory(CertificateInfoFactory.class);

    public CertificateInfo newCertificateInfo() {
        return FACTORY.newEntity(null);
    }

    public CertificateInfoCreator newCreator() {
        return FACTORY.newCreator(null);
    }

    public CertificateInfoQuery newQuery() {
        return FACTORY.newQuery(null);
    }

    public CertificateInfoListResult newListResult() {
        return FACTORY.newListResult();
    }

    public CertificateUsage newCertificateUsage() {
        return FACTORY.newCertificateUsage(null);
    }

}
