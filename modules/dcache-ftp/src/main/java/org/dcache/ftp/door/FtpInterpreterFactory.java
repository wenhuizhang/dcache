/* dCache - http://www.dcache.org/
 *
 * Copyright (C) 2015 Deutsches Elektronen-Synchrotron
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.dcache.ftp.door;

import java.net.InetSocketAddress;
import java.util.concurrent.Executor;

import diskCacheV111.doors.LineBasedInterpreter;
import diskCacheV111.doors.NettyLineBasedInterpreterFactory;
import diskCacheV111.util.ConfigurationException;

import dmg.cells.nucleus.CellAddressCore;
import dmg.cells.nucleus.CellEndpoint;
import dmg.util.LineWriter;

import org.dcache.poolmanager.PoolManagerHandler;
import org.dcache.util.Args;
import org.dcache.util.OptionParser;

public abstract class FtpInterpreterFactory implements NettyLineBasedInterpreterFactory
{
    protected final FtpDoorSettings settings = new FtpDoorSettings();

    protected abstract AbstractFtpDoorV1 createInterpreter() throws Exception;

    @Override
    public void configure(Args args) throws ConfigurationException
    {
        OptionParser options = new OptionParser(args);
        options.inject(settings);
        options.inject(this);
    }

    @Override
    public LineBasedInterpreter create(CellEndpoint endpoint, CellAddressCore myAddress,
                                       InetSocketAddress remoteAddress, InetSocketAddress localAddress,
                                       LineWriter writer, Executor executor, PoolManagerHandler poolManagerHandler)
            throws Exception
    {
        AbstractFtpDoorV1 interpreter = createInterpreter();
        interpreter.setSettings(settings);
        interpreter.setWriter(writer);
        interpreter.setRemoteSocketAddress(remoteAddress);
        interpreter.setLocalSocketAddress(localAddress);
        interpreter.setExecutor(executor);
        interpreter.setCellEndpoint(endpoint);
        interpreter.setCellAddress(myAddress);
        interpreter.setPoolManagerHandler(poolManagerHandler);
        interpreter.init();
        return interpreter;
    }
}
